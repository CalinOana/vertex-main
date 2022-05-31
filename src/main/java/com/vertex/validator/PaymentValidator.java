package com.vertex.validator;

import com.vertex.api.generated.models.PaymentDTO;
import com.vertex.exceptions.InvalidInputParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.vertex.api.generated.models.EnumCurrency;

import static com.vertex.api.generated.models.EnumCurrency.EUR;
import static com.vertex.api.generated.models.EnumCurrency.USD;
import static com.vertex.exceptions.ErrorKey.withKey;
import static com.vertex.exceptions.ExceptionMessageKeyConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentValidator extends BaseValidator {

    public void validatePaymentOnCreation(PaymentDTO paymentDTO) {
        validateFieldNotNull(paymentDTO, withKey(NULL_PAYMENT));
        validateFieldNotNull(paymentDTO.getAmount(), withKey(NULL_PAYMENT_AMOUNT));
        validateFieldNotNull(paymentDTO.getCurrency(), withKey(NULL_PAYMENT_CURRENCY));
        validateFieldNotNull(paymentDTO.getType(), withKey(NULL_PAYMENT_TYPE));
        validateFieldNotNull(paymentDTO.getDebtorIban(), withKey(NULL_PAYMENT_DEBTOR_IBAN));
        validateFieldNotNull(paymentDTO.getCreditorIban(), withKey(NULL_PAYMENT_CREDITOR_IBAN));

        validatePaymentTypeConstraints(paymentDTO);
    }

    public void validatePaymentTypeConstraints(PaymentDTO paymentDTO) {
        switch(paymentDTO.getType()){
            case TYPE1:
                validateCurrency(EUR,paymentDTO.getCurrency());
                validateFieldNotNull(paymentDTO.getDetails(),withKey(NULL_PAYMENT_DETAILS));
                break;
            case TYPE2:
                validateCurrency(USD,paymentDTO.getCurrency());
                break;
            case TYPE3:
                validateFieldNotNull(paymentDTO.getBicCode(),withKey(NULL_PAYMENT_BIC_CODE));
                break;
        }
    }

    private void validateCurrency(EnumCurrency expectedCurrency, EnumCurrency actualCurrency) {
        if(!expectedCurrency.equals(actualCurrency)){
            throw new InvalidInputParameterException(withKey(INCOMPATIBLE_TYPE_CURRENCY));
        }
    }
}
