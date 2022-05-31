package com.vertex;

import com.vertex.components.cast.ModelMapperExtended;
import com.vertex.repository.PaymentRepository;
import com.vertex.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VertexApplication.class)
public class BaseServiceIntTest {

    @Autowired
    public PaymentRepository paymentRepository;

    @Autowired
    public PaymentService paymentService;

    @Autowired
    public ModelMapperExtended modelMapperExtended;

    public MockMvc mockMvc;

    @Test
    void setUpTest() {
        assertNotNull(paymentRepository);
        assertNotNull(paymentService);
    }
}
