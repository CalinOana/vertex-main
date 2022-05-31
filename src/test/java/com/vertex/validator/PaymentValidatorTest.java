package com.vertex.validator;

import com.vertex.api.generated.models.PaymentDTO;
import com.vertex.exceptions.InvalidInputParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.vertex.DataMock.mockValidPaymentDTO;
import static com.vertex.api.generated.models.EnumCurrency.EUR;
import static com.vertex.api.generated.models.EnumCurrency.USD;
import static com.vertex.api.generated.models.EnumPaymentType.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PaymentValidatorTest {

    private PaymentValidator paymentValidator;

    @BeforeEach
    void setup() {
        paymentValidator = new PaymentValidator();
    }

    @Test
    @DisplayName("Given valid paymentDTO when validatePaymentOnCreation assert does not fail")
    void validatePaymentOnCreationTestPositive() {
        assertDoesNotThrow(() -> paymentValidator.validatePaymentOnCreation(mockValidPaymentDTO()));
    }

    @Test
    @DisplayName("Given invalid paymentDTO when validatePaymentOnCreation assert throws correct exceptions")
    void validatePaymentOnCreationTestNegative() {
        InvalidInputParameterException invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> paymentValidator.validatePaymentOnCreation(null));
        assertEquals("null.payment", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class,
                () -> paymentValidator.validatePaymentOnCreation(mockValidPaymentDTO().amount(null)));
        assertEquals("null.payment.amount", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class,
                () -> paymentValidator.validatePaymentOnCreation(mockValidPaymentDTO().currency(null)));
        assertEquals("null.payment.currency", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class,
                () -> paymentValidator.validatePaymentOnCreation(mockValidPaymentDTO().type(null)));
        assertEquals("null.payment.type", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class,
                () -> paymentValidator.validatePaymentOnCreation(mockValidPaymentDTO().debtorIban(null)));
        assertEquals("null.payment.debtor.iban", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(InvalidInputParameterException.class,
                () -> paymentValidator.validatePaymentOnCreation(mockValidPaymentDTO().creditorIban(null)));
        assertEquals("null.payment.creditor.iban", invalidInputParameterException.getErrorKey().getKey());

    }

    @Test
    @DisplayName("Given invalid paymentDTO when validatePaymentTypeConstraints assert throws correct exceptions")
    void validatePaymentCreationValidateTypeConstraintsTestNegative() {
        final PaymentDTO payment = mockValidPaymentDTO();
        InvalidInputParameterException invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> {
                    paymentValidator.validatePaymentTypeConstraints(payment.type(TYPE1).currency(USD));
                });
        assertEquals("incompatible.type.currency", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> {
                    paymentValidator.validatePaymentTypeConstraints(payment.type(TYPE1).currency(EUR).details(null));
                });
        assertEquals("null.payment.details", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> {
                    paymentValidator.validatePaymentTypeConstraints(payment.type(TYPE2).currency(EUR).details(null));
                });
        assertEquals("incompatible.type.currency", invalidInputParameterException.getErrorKey().getKey());

        invalidInputParameterException = assertThrows(
                InvalidInputParameterException.class, () -> {
                    paymentValidator.validatePaymentTypeConstraints(payment.type(TYPE3).bicCode(null));
                });
        assertEquals("null.payment.bic.code", invalidInputParameterException.getErrorKey().getKey());
    }
}
