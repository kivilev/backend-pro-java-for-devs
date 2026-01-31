package ru.backendpro.controller.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.backendpro.controller.dto.ClientResponseDto;
import ru.backendpro.entity.Client;
import ru.backendpro.entity.ClientData;
import ru.backendpro.entity.ClientDataField;
import ru.backendpro.entity.ClientDataFieldEnum;
import ru.backendpro.entity.ClientStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("ClientMapper Unit Tests")
class ClientMapperTest {

    private ClientMapper clientMapper;

    @BeforeEach
    void setUp() {
        clientMapper = new ClientMapper();
    }

    @Test
    @DisplayName("Should map Client to ClientResponseDto successfully with all fields")
    void mapToResponse_WhenClientWithAllFields_ShouldReturnCorrectDto() {
        // Given
        ClientDataField firstNameField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.FIRST_NAME.getId())
                .name("First Name")
                .description("Client first name")
                .build();
        
        ClientDataField lastNameField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.LAST_NAME.getId())
                .name("Last Name")
                .description("Client last name")
                .build();

        ClientData firstNameData = ClientData.builder()
                .field(firstNameField)
                .fieldValue("John")
                .build();

        ClientData lastNameData = ClientData.builder()
                .field(lastNameField)
                .fieldValue("Doe")
                .build();

        List<ClientData> clientDataList = new ArrayList<>();
        clientDataList.add(firstNameData);
        clientDataList.add(lastNameData);

        Client client = Client.builder()
                .id(1L)
                .email("john.doe@example.com")
                .phoneNumber("+1234567890")
                .status(ClientStatus.ACTIVE)
                .clientData(clientDataList)
                .build();

        // When
        ClientResponseDto result = clientMapper.mapToResponse(client);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("+1234567890", result.getPhoneNumber());
        assertEquals(ClientStatus.ACTIVE, result.getStatus());
        assertNotNull(result.getProperties());
        assertEquals(2, result.getProperties().size());
        assertEquals("John", result.getProperties().get("firstName"));
        assertEquals("Doe", result.getProperties().get("lastName"));
    }

    @Test
    @DisplayName("Should map Client to ClientResponseDto when client has no data")
    void mapToResponse_WhenClientWithoutData_ShouldReturnDtoWithEmptyProperties() {
        // Given
        Client client = Client.builder()
                .id(2L)
                .email("client@example.com")
                .phoneNumber(null)
                .status(ClientStatus.ACTIVE)
                .clientData(new ArrayList<>())
                .build();

        // When
        ClientResponseDto result = clientMapper.mapToResponse(client);

        // Then
        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("client@example.com", result.getEmail());
        assertNull(result.getPhoneNumber());
        assertEquals(ClientStatus.ACTIVE, result.getStatus());
        assertNotNull(result.getProperties());
        assertTrue(result.getProperties().isEmpty());
    }

    @Test
    @DisplayName("Should map Client to ClientResponseDto when status is INACTIVE")
    void mapToResponse_WhenClientStatusIsDeactivated_ShouldReturnDeactivatedStatus() {
        // Given
        Client client = Client.builder()
                .id(3L)
                .email("deactivated@example.com")
                .phoneNumber("+9876543210")
                .status(ClientStatus.INACTIVE)
                .clientData(new ArrayList<>())
                .build();

        // When
        ClientResponseDto result = clientMapper.mapToResponse(client);

        // Then
        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertEquals(ClientStatus.INACTIVE, result.getStatus());
    }

    @Test
    @DisplayName("Should map Client to ClientResponseDto when status is BLOCKED")
    void mapToResponse_WhenClientStatusIsBlocked_ShouldReturnBlockedStatus() {
        // Given
        Client client = Client.builder()
                .id(4L)
                .email("blocked@example.com")
                .phoneNumber("+1111111111")
                .status(ClientStatus.BLOCKED)
                .clientData(new ArrayList<>())
                .build();

        // When
        ClientResponseDto result = clientMapper.mapToResponse(client);

        // Then
        assertNotNull(result);
        assertEquals(4L, result.getId());
        assertEquals(ClientStatus.BLOCKED, result.getStatus());
    }

    @Test
    @DisplayName("Should map Client to ClientResponseDto with all client data field types")
    void mapToResponse_WhenClientWithAllDataFieldTypes_ShouldMapAllProperties() {
        // Given
        ClientDataField firstNameField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.FIRST_NAME.getId())
                .build();
        ClientDataField lastNameField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.LAST_NAME.getId())
                .build();
        ClientDataField birthdayField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.BIRTHDAY.getId())
                .build();
        ClientDataField passportSeriesField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.PASSPORT_SERIES.getId())
                .build();
        ClientDataField passportNumberField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.PASSPORT_NUMBER.getId())
                .build();
        ClientDataField middleNameField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.MIDDLE_NAME.getId())
                .build();

        List<ClientData> clientDataList = new ArrayList<>();
        clientDataList.add(ClientData.builder().field(firstNameField).fieldValue("John").build());
        clientDataList.add(ClientData.builder().field(lastNameField).fieldValue("Doe").build());
        clientDataList.add(ClientData.builder().field(birthdayField).fieldValue("1990-01-01").build());
        clientDataList.add(ClientData.builder().field(passportSeriesField).fieldValue("1234").build());
        clientDataList.add(ClientData.builder().field(passportNumberField).fieldValue("567890").build());
        clientDataList.add(ClientData.builder().field(middleNameField).fieldValue("Michael").build());

        Client client = Client.builder()
                .id(5L)
                .email("full@example.com")
                .phoneNumber("+1234567890")
                .status(ClientStatus.ACTIVE)
                .clientData(clientDataList)
                .build();

        // When
        ClientResponseDto result = clientMapper.mapToResponse(client);

        // Then
        assertNotNull(result);
        assertNotNull(result.getProperties());
        assertEquals(6, result.getProperties().size());
        assertEquals("John", result.getProperties().get("firstName"));
        assertEquals("Doe", result.getProperties().get("lastName"));
        assertEquals("1990-01-01", result.getProperties().get("birthday"));
        assertEquals("1234", result.getProperties().get("passportSeries"));
        assertEquals("567890", result.getProperties().get("passportNumber"));
        assertEquals("Michael", result.getProperties().get("middleName"));
    }

    @Test
    @DisplayName("Should map Client to ClientResponseDto when phone number is null")
    void mapToResponse_WhenClientPhoneNumberIsNull_ShouldReturnNullPhoneNumber() {
        // Given
        Client client = Client.builder()
                .id(6L)
                .email("nophone@example.com")
                .phoneNumber(null)
                .status(ClientStatus.ACTIVE)
                .clientData(new ArrayList<>())
                .build();

        // When
        ClientResponseDto result = clientMapper.mapToResponse(client);

        // Then
        assertNotNull(result);
        assertEquals(6L, result.getId());
        assertNull(result.getPhoneNumber());
    }

    @Test
    @DisplayName("Should map Client to ClientResponseDto with duplicate data fields")
    void mapToResponse_WhenClientWithDuplicateDataFields_ShouldMapLastValue() {
        // Given
        ClientDataField firstNameField = ClientDataField.builder()
                .fieldId(ClientDataFieldEnum.FIRST_NAME.getId())
                .build();

        List<ClientData> clientDataList = new ArrayList<>();
        clientDataList.add(ClientData.builder().field(firstNameField).fieldValue("John").build());
        clientDataList.add(ClientData.builder().field(firstNameField).fieldValue("Jane").build());

        Client client = Client.builder()
                .id(7L)
                .email("duplicate@example.com")
                .phoneNumber("+1234567890")
                .status(ClientStatus.ACTIVE)
                .clientData(clientDataList)
                .build();

        // When
        ClientResponseDto result = clientMapper.mapToResponse(client);

        // Then
        assertNotNull(result);
        assertNotNull(result.getProperties());
        assertEquals(1, result.getProperties().size());
        assertEquals("Jane", result.getProperties().get("firstName"));
    }
}

