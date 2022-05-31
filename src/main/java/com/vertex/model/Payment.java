package com.vertex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="payment")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name= "amount")
    private BigDecimal amount;

    @Column (name="currency")
    @Enumerated(EnumType.STRING)
    private com.vertex.api.generated.models.EnumCurrency currency;

    @Column (name="type")
    @Enumerated(EnumType.STRING)
    private com.vertex.api.generated.models.EnumPaymentType type;

    @Column(name = "debtor_iban")
    private String debtorIban;

    @Column(name = "creditor_iban")
    private String creditorIban;

    @Column(name = "details")
    private String details;

    @Column(name = "bic_code")
    private String bicCode;
}
