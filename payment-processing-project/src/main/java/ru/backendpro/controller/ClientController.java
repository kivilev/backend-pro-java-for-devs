package ru.backendpro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.backendpro.controller.dto.ClientCreateRequestDto;
import ru.backendpro.controller.dto.ClientResponseDto;
import ru.backendpro.controller.dto.ClientStatusUpdateRequestDto;
import ru.backendpro.controller.dto.ClientUpdateRequestDto;
import ru.backendpro.controller.mapper.ClientMapper;
import ru.backendpro.entity.Client;
import ru.backendpro.service.ClientService;

@Slf4j
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Tag(name = "Client", description = "Client management APIs")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    @Operation(summary = "Create a new client")
    public ResponseEntity<ClientResponseDto> createClient(@Valid @RequestBody ClientCreateRequestDto request) {
        log.info("POST /api/v1/clients - Creating client: email={}", request.getEmail());
        Client client = clientService.createClient(request);
        ClientResponseDto response = clientMapper.mapToResponse(client);
        log.info("Client created via API: id={}, email={}", client.getId(), client.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID")
    public ClientResponseDto getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return clientMapper.mapToResponse(client);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update client properties")
    public ClientResponseDto updateClient(@PathVariable Long id,
                                          @Valid @RequestBody ClientUpdateRequestDto request) {
        log.info("PATCH /api/v1/clients/{} - Updating client", id);
        Client client = clientService.updateClient(id, request);
        log.info("Client updated via API: id={}", id);
        return clientMapper.mapToResponse(client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deactivate client")
    public ResponseEntity<Void> deactivateClient(@PathVariable Long id) {
        log.info("DELETE /api/v1/clients/{} - Deactivating client", id);
        clientService.deactivateClient(id);
        log.info("Client deactivated via API: id={}", id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update client status")
    public ClientResponseDto updateClientStatus(@PathVariable Long id,
                                                @Valid @RequestBody ClientStatusUpdateRequestDto request) {
        log.info("PATCH /api/v1/clients/{}/status - Updating client status: newStatus={}", id, request.getStatus());
        Client client = clientService.updateClientStatus(id, request.getStatus());
        log.info("Client status updated via API: id={}, status={}", id, client.getStatus());
        return clientMapper.mapToResponse(client);
    }
}
