package com.vertex.exceptions;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ExceptionMessageKeyConstants {
    public static final String NULL_PAYMENT = "null.payment";
    public static final String NULL_PAYMENT_AMOUNT = "null.payment.amount";
    public static final String NULL_PAYMENT_CURRENCY = "null.payment.currency";
    public static final String NULL_PAYMENT_TYPE = "null.payment.type";
    public static final String NULL_PAYMENT_DEBTOR_IBAN = "null.payment.debtor.iban";
    public static final String NULL_PAYMENT_CREDITOR_IBAN = "null.payment.creditor.iban";
    public static final String NULL_PAYMENT_BIC_CODE = "null.payment.bic.code";
    public static final String NULL_PAYMENT_DETAILS = "null.payment.details";

    public static final String INCOMPATIBLE_TYPE_CURRENCY = "incompatible.type.currency";
}
