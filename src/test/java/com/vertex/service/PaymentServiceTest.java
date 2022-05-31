package com.vertex.service;

import com.vertex.components.cast.ModelMapperExtended;
import com.vertex.repository.PaymentRepository;
import com.vertex.validator.PaymentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    PaymentService paymentService;

    @Mock
    PaymentRepository paymentRepositoryMock;

    @Mock
    PaymentValidator paymentValidatorMock;

    @BeforeEach
    void setup() {
        paymentService = new PaymentService(paymentRepositoryMock, new ModelMapperExtended(new ModelMapper()), paymentValidatorMock);
    }

}
