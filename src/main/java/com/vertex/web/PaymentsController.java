package com.vertex.web;

import com.vertex.api.generated.models.PaymentDTO;
import com.vertex.service.PaymentService;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class PaymentsController implements com.vertex.api.generated.controllers.PaymentsApi {
    private final PaymentService paymentService;

    @Override
    public ResponseEntity<List<com.vertex.api.generated.models.PaymentDTO>> fetchPayments() {
        return ResponseEntity.ok(paymentService.getPayments());
    }

    @Override
    public ResponseEntity<PaymentDTO> paymentsPost(@ApiParam(value = "Body containing PaymentDTO to add", required = true)
                                                   @Valid @RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDTO));
    }
}
