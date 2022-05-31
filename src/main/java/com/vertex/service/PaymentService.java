package com.vertex.service;

import com.vertex.api.generated.models.PaymentDTO;
import com.vertex.components.cast.ModelMapperExtended;
import com.vertex.model.Payment;
import com.vertex.repository.PaymentRepository;
import com.vertex.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ModelMapperExtended modelMapperExtended;
    private final PaymentValidator paymentValidator;

    @Transactional
    public List<PaymentDTO> getPayments() {
        final List<Payment> all = paymentRepository.findAll();
        return modelMapperExtended.mapAll(all, PaymentDTO.class);
    }

    @Transactional
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        paymentValidator.validatePaymentOnCreation(paymentDTO);
        final Payment savedPayment = paymentRepository.save(modelMapperExtended.map(paymentDTO,Payment.class));
        return modelMapperExtended.map(savedPayment, PaymentDTO.class);
    }
}
