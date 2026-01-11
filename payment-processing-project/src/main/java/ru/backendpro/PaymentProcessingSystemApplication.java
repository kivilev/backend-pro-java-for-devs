package ru.backendpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class PaymentProcessingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentProcessingSystemApplication.class, args);

        String text = "Java Programming";

        String upper = text.toUpperCase(); // "JAVA PROGRAMMING"
        String lower = upper.toLowerCase(); // "java programming"
        System.out.println(lower);
    }
}
