package com.isilona.hrm.dao.repository;

import com.isilona.hrm.dao.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    private Employee testEmployee;
    private Address testAddress;
    private Contact testContact;
    private PaymentInfo testPaymentInfo;

    @BeforeEach
    public void initEach() {
        testEmployee = new Employee();
        testEmployee.setFirstName("FName");
        testEmployee.setLastName("LName");

        testAddress = new Address();
        testAddress.setAddressLine("Address Line");
        testAddress.setCity("City");
        testAddress.setCountry("Country");
        testAddress.setPostCode("1000");

        testContact = new Contact();
        testContact.setType(ContactType.TELEPHONE);
        testContact.setValue("+359 00 11 22 33 4");

        testPaymentInfo = new PaymentInfo();
        testPaymentInfo.setIban("GB94BARC10201530093459");
        testPaymentInfo.setSalary(new BigDecimal(10000));

    }

    @Test
    public void whenSave_thenReturnSavedEntity() {
        // given testEmployee

        // when
        Employee savedEmployee = repository.save(testEmployee);

        // then
        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getUuid()).isNotNull();
        assertThat(savedEmployee.getCreatedAt()).isNotNull();
        assertThat(savedEmployee.getUpdatedAt()).isNull();
        assertThat(savedEmployee.getVersion()).isEqualTo(0);

    }

    @Test
    public void whenEmployeeFirstNameIsNotSet_thenThrowException() {
        // given
        testEmployee.setFirstName(null);

        // when
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> repository.save(testEmployee));

        // then

        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("propertyPath=firstName"));
    }

    @Test
    public void whenEmployeeLastNameIsNotSet_thenThrowException() {
        // given
        testEmployee.setLastName(null);

        // when
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> repository.save(testEmployee));

        // then

        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("propertyPath=lastName"));
    }

    // Address

    @Test
    public void whenSaveAddressAdded_thenReturnSavedEntity() {

        // given
        testEmployee.setAddress(testAddress);

        // when
        Employee savedEmployee = repository.save(testEmployee);

        // then
        assertThat(savedEmployee.getAddress().getId()).isNotNull();
        assertThat(savedEmployee.getAddress().getUuid()).isNotNull();
        assertThat(savedEmployee.getAddress().getCreatedAt()).isNotNull();
        assertThat(savedEmployee.getAddress().getUpdatedAt()).isNull();
        assertThat(savedEmployee.getAddress().getVersion()).isEqualTo(0);

    }

    @Test
    public void whenAddressMissAddressLine_thenExceptionIsThrown() {

        // given
        testAddress.setAddressLine(null);
        testEmployee.setAddress(testAddress);

        // when
        Exception exception = assertThrows(
                TransactionSystemException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getCause().getCause().getMessage().contains("Validation failed"));
        assertTrue(exception.getCause().getCause().getMessage().contains("propertyPath=addressLine"));
    }

    @Test
    public void whenAddressMissCity_thenExceptionIsThrown() {

        // given
        testAddress.setCity(null);
        testEmployee.setAddress(testAddress);

        // when
        Exception exception = assertThrows(
                TransactionSystemException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getCause().getCause().getMessage().contains("Validation failed"));
        assertTrue(exception.getCause().getCause().getMessage().contains("propertyPath=city"));
    }

    @Test
    public void whenAddressMissCountry_thenExceptionIsThrown() {

        // given
        testAddress.setCountry(null);
        testEmployee.setAddress(testAddress);

        // when
        Exception exception = assertThrows(
                TransactionSystemException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getCause().getCause().getMessage().contains("Validation failed"));
        assertTrue(exception.getCause().getCause().getMessage().contains("propertyPath=country"));
    }

    @Test
    public void whenAddressMissPostCode_thenExceptionIsThrown() {

        // given
        testAddress.setPostCode(null);
        testEmployee.setAddress(testAddress);

        // when
        Exception exception = assertThrows(
                TransactionSystemException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getCause().getCause().getMessage().contains("Validation failed"));
        assertTrue(exception.getCause().getCause().getMessage().contains("propertyPath=postCode"));
    }

    // CONTACTS

    @Test
    public void whenSaveContactAdded_thenReturnSavedEntity() {

        // given
        testEmployee.setContacts(Set.of(testContact));

        // when
        Employee savedEmployee = repository.save(testEmployee);

        // then
        assertThat(savedEmployee.getContacts()).isNotNull();

    }

    @Test
    public void whenContactMissType_thenExceptionIsThrown() {

        // given
        testContact.setType(null);
        testEmployee.setContacts(Set.of(testContact));

        // when
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("propertyPath=type"));
    }

    @Test
    public void whenContactMissValue_thenExceptionIsThrown() {

        // given
        testContact.setValue(null);
        testEmployee.setContacts(Set.of(testContact));

        // when
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("propertyPath=value"));
    }

    // PAYMENT INFO

    @Test
    public void whenSavePaymentInfoAdded_thenReturnSavedEntity() {

        // given
        testEmployee.setPaymentInfo(testPaymentInfo);

        // when
        Employee savedEmployee = repository.save(testEmployee);

        // then
        assertThat(savedEmployee.getPaymentInfo()).isNotNull();

    }

    @Test
    public void whenPaymentInfoMissIban_thenExceptionIsThrown() {

        // given
        testPaymentInfo.setIban(null);
        testEmployee.setPaymentInfo(testPaymentInfo);

        // when
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("propertyPath=iban"));
    }

    @Test
    public void whenPaymentInfoMissSalary_thenExceptionIsThrown() {

        // given
        testPaymentInfo.setSalary(null);
        testEmployee.setPaymentInfo(testPaymentInfo);

        // when
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> repository.save(testEmployee));

        // then
        assertTrue(exception.getMessage().contains("Validation failed"));
        assertTrue(exception.getMessage().contains("propertyPath=salary"));
    }

}