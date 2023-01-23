package vn.slf.handlers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.slf.functions.EmailFunction;
import vn.slf.models.EmailRequest;
import vn.slf.models.EmailResponse;

import java.util.function.Function;

@SpringBootApplication(scanBasePackages = {"com.slf"})
public class FunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }

    @Bean
    public Function<EmailRequest, EmailResponse> handle() {
        return new EmailFunction();
    }
}
