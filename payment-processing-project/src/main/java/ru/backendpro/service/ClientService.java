package ru.backendpro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backendpro.dao.repository.ClientDataFieldRepository;
import ru.backendpro.dao.repository.ClientRepository;
import ru.backendpro.controller.dto.ClientCreateRequestDto;
import ru.backendpro.controller.dto.ClientUpdateRequestDto;
import ru.backendpro.entity.Client;
import ru.backendpro.entity.ClientData;
import ru.backendpro.entity.ClientDataField;
import ru.backendpro.entity.ClientDataFieldEnum;
import ru.backendpro.entity.ClientStatus;
import ru.backendpro.exception.BusinessException;
import ru.backendpro.exception.ClientLockTimeoutException;
import ru.backendpro.exception.ResourceNotFoundException;

import jakarta.persistence.LockTimeoutException;
import jakarta.persistence.PessimisticLockException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private static final String DEFAULT_CURRENCY_CODE = "RUB";
    private final ClientRepository clientRepository;
    private final ClientDataFieldRepository clientDataFieldRepository;
    private final AccountService accountService;
    private final ClientValidationService clientValidationService;

    @Transactional
    public Client createClient(ClientCreateRequestDto request) {
        log.info("Creating client: email={}", request.getEmail());
        clientValidationService.validateClientProperties(request.getProperties());

        if (clientRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Client with email already exists: email={}", request.getEmail());
            throw new BusinessException("Client with email " + request.getEmail() + " already exists");
        }

        Client client = Client.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .status(ClientStatus.ACTIVE)
                .build();

        if (request.getProperties() != null) {
            log.debug("Adding client data: {} fields", request.getProperties().size());
            for (Map.Entry<String, String> entry : request.getProperties().entrySet()) {
                ClientDataFieldEnum fieldEnum = ClientDataFieldEnum.fromJsonFieldName(entry.getKey());
                //TODO: cache dictionary
                ClientDataField field = clientDataFieldRepository.findById(fieldEnum.getId())
                        .orElseThrow(() -> {
                            log.error("ClientDataField not found: id={}", fieldEnum.getId());
                            return new BusinessException("ClientDataField with id '" + fieldEnum.getId() + "' not found");
                        });
                ClientData data = ClientData.builder()
                        .client(client)
                        .field(field)
                        .fieldValue(entry.getValue())
                        .build();
                client.getClientData().add(data);
            }
        }

        var newClient = clientRepository.save(client);
        log.info("Client created successfully: id={}, email={}", newClient.getId(), newClient.getEmail());

        accountService.createAccount(newClient.getId(), DEFAULT_CURRENCY_CODE, BigDecimal.ZERO);

        // TODO: send message to Kafka

        return newClient;
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findByIdWithData(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    @Transactional
    public Client updateClient(Long id, ClientUpdateRequestDto request) {
        log.info("Updating client: id={}", id);
        try {
            Client client = clientRepository.findByIdWithDataAndLock(id)
                    .orElseThrow(() -> {
                        log.error("Client not found for update: id={}", id);
                        return new ResourceNotFoundException("Client not found with id: " + id);
                    });

            checkAndThrowIfCouldNotBeChanged(client);

            if (request.getProperties() != null && !request.getProperties().isEmpty()) {
                log.debug("Updating client data: id={}, fieldsCount={}", id, request.getProperties().size());
                Map<Integer, ClientData> existingDataMap = new HashMap<>();
                for (ClientData data : client.getClientData()) {
                    existingDataMap.put(data.getField().getFieldId(), data);
                }

                for (Map.Entry<String, String> entry : request.getProperties().entrySet()) {
                    ClientDataFieldEnum fieldEnum = ClientDataFieldEnum.fromJsonFieldName(entry.getKey());
                    ClientDataField field = clientDataFieldRepository.findById(fieldEnum.getId())
                            .orElseThrow(() -> {
                                log.error("ClientDataField not found: id={}", fieldEnum.getId());
                                return new BusinessException("ClientDataField with id '" + fieldEnum.getId() + "' not found");
                            });

                    ClientData existingData = existingDataMap.get(field.getFieldId());
                    if (existingData != null) {
                        existingData.setFieldValue(entry.getValue());
                        existingDataMap.remove(field.getFieldId());
                    } else {
                        ClientData newData = ClientData.builder()
                                .client(client)
                                .field(field)
                                .fieldValue(entry.getValue())
                                .build();
                        client.getClientData().add(newData);
                    }
                }

                for (ClientData dataToRemove : existingDataMap.values()) {
                    client.getClientData().remove(dataToRemove);
                }
            }

            Client savedClient = clientRepository.save(client);
            log.info("Client updated successfully: id={}", id);
            return savedClient;
        } catch (LockTimeoutException | PessimisticLockException e) {
            log.warn("Client lock timeout during update: id={}", id, e);
            throw new ClientLockTimeoutException(
                    "Client lock timeout for client id: " + id + ". Client is locked by another session.", e);
        }
    }

    @Transactional
    public void deactivateClient(Long id) {
        log.info("Deactivating client: id={}", id);
        try {
            Client client = clientRepository.findByIdWithDataAndLock(id)
                    .orElseThrow(() -> {
                        log.error("Client not found for deactivation: id={}", id);
                        return new ResourceNotFoundException("Client not found with id: " + id);
                    });
            
            if (client.getStatus() == ClientStatus.INACTIVE) {
                log.debug("Client is already deactivated: id={}", id);
                return;
            }
            
            ClientStatus oldStatus = client.getStatus();
            client.setStatus(ClientStatus.INACTIVE);
            clientRepository.save(client);
            log.info("Client deactivated successfully: id={}, oldStatus={}", id, oldStatus);
            
            // TODO: Отправить событие в Kafka о деактивации клиента (client.deactivated)
        } catch (LockTimeoutException | PessimisticLockException e) {
            log.warn("Client lock timeout during deactivation: id={}", id, e);
            throw new ClientLockTimeoutException(
                    "Client lock timeout for client id: " + id + ". Client is locked by another session.", e);
        }
    }

    @Transactional
    public Client updateClientStatus(Long id, ClientStatus newStatus) {
        log.info("Updating client status: id={}, newStatus={}", id, newStatus);
        try {
            Client client = clientRepository.findByIdWithDataAndLock(id)
                    .orElseThrow(() -> {
                        log.error("Client not found for status update: id={}", id);
                        return new ResourceNotFoundException("Client not found with id: " + id);
                    });
            checkAndThrowIfCouldNotBeChanged(client);
            
            if (client.getStatus() == newStatus) {
                log.warn("Client is already in requested status: id={}, status={}", id, newStatus);
                throw new BusinessException("Client is already in status: " + newStatus);
            }
            
            ClientStatus oldStatus = client.getStatus();
            client.setStatus(newStatus);
            Client savedClient = clientRepository.save(client);
            log.info("Client status updated: id={}, oldStatus={}, newStatus={}", id, oldStatus, newStatus);
            
            // TODO: Отправить событие в Kafka об изменении статуса клиента (client.status.changed)
            
            return savedClient;
        } catch (LockTimeoutException | PessimisticLockException e) {
            log.warn("Client lock timeout during status update: id={}", id, e);
            throw new ClientLockTimeoutException(
                    "Client lock timeout for client id: " + id + ". Client is locked by another session.", e);
        }
    }

    private void checkAndThrowIfCouldNotBeChanged(Client client){
        if(client.getStatus().isFinal()){
            throw new BusinessException("Client status is final. Can't be changed");
        }
    }
}
