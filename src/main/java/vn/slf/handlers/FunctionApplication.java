package vn.slf.handlers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.slf.functions.TemplateFunction;
import vn.slf.models.TemplateRequest;
import vn.slf.models.TemplateResponse;

import java.util.function.Function;

@SpringBootApplication(scanBasePackages = {"com.slf"})
public class FunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }

    @Bean
    public Function<TemplateRequest, TemplateResponse> handle() {
        return new TemplateFunction();
    }
}
