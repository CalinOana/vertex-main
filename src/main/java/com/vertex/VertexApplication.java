package com.vertex;

import com.vertex.api.generated.models.EnumCurrency;
import com.vertex.api.generated.models.EnumPaymentType;
import com.vertex.model.Payment;
import com.vertex.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@SpringBootApplication(scanBasePackages = {
        "com.vertex",
        "com.vertex.api.generated"
})
@RequiredArgsConstructor
public class VertexApplication {

    private final PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(VertexApplication.class, args);
    }

    @PostConstruct
    public void started() {
        createInitialDataset();
    }

    private void createInitialDataset() {
        paymentRepository.saveAll(new HashSet<>(Arrays.asList(
                new Payment().toBuilder().id(UUID.randomUUID()).amount(BigDecimal.TEN).debtorIban("asderqdas").creditorIban("asdsdad").type(EnumPaymentType.TYPE1).currency(EnumCurrency.EUR).build(),
                new Payment().toBuilder().id(UUID.randomUUID()).amount(BigDecimal.TEN).debtorIban("asderqdas").creditorIban("asdsdad").type(EnumPaymentType.TYPE1).currency(EnumCurrency.USD).build())));
    }
}
