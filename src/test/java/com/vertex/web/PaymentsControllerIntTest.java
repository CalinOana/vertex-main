package com.vertex.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vertex.BaseServiceIntTest;
import com.vertex.DataMock;
import com.vertex.api.generated.models.PaymentDTO;
import com.vertex.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PaymentsControllerIntTest extends BaseServiceIntTest {

    private static final String PAYMENT_API_BASE_PATH = "/payments";

    @BeforeEach
    void setup() {
        final PaymentsController paymentsController = new PaymentsController(paymentService);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentsController).build();
        paymentRepository.deleteAll();
    }

    @Test
    @DisplayName("Given GET request to Payments controller assert that status is ok and response is correct")
    @Transactional
    @Rollback
    void paymentsGetTest() throws Exception {
        paymentRepository.save(DataMock.mockValidPayment());
        final MvcResult mvcResult = mockMvc.perform(get(PAYMENT_API_BASE_PATH).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<PaymentDTO> payments = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, PaymentDTO.class));
        assertEquals(1, payments.size());
        final PaymentDTO paymentDTO = payments.get(0);
        assertAll(() -> assertEquals("IBAN1", paymentDTO.getDebtorIban()),
                () -> assertEquals("IBAN2", paymentDTO.getCreditorIban()),
                () -> assertEquals("BIC Code 1", paymentDTO.getBicCode())
        );
    }

}
