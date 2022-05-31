package com.vertex;

import com.vertex.api.generated.models.PaymentDTO;
import com.vertex.model.Payment;

import java.math.BigDecimal;
import java.util.UUID;

import static com.vertex.api.generated.models.EnumCurrency.EUR;
import static com.vertex.api.generated.models.EnumPaymentType.TYPE1;
import static com.vertex.api.generated.models.EnumPaymentType.TYPE3;

public class DataMock {

    public static com.vertex.api.generated.models.PaymentDTO mockValidPaymentDTO() {
        return new PaymentDTO().id(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .debtorIban("IBAN1")
                .creditorIban("IBAN2")
                .type(TYPE3)
                .currency(EUR)
                .bicCode("BIC Code 1");
    }

    public static Payment mockValidPayment() {
        return new Payment().toBuilder().id(UUID.randomUUID())
                .amount(BigDecimal.TEN)
                .debtorIban("IBAN1")
                .creditorIban("IBAN2")
                .type(TYPE1)
                .currency(EUR)
                .bicCode("BIC Code 1")
                .build();
    }
}
