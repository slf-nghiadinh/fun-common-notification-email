package com.sunlife.vn.handlers;

import com.sunlife.vn.models.TaskRequest;
import com.sunlife.vn.models.TaskResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.sunlife.vn.functions.TaskFunction;

import java.util.function.Function;

@SpringBootApplication(scanBasePackages = {"com.sunlife.vn"})
public class FunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionApplication.class, args);
    }

    @Bean
    public Function<TaskRequest, TaskResponse> handle() {
        System.out.println("Start Function");
        return new TaskFunction();
    }
}
