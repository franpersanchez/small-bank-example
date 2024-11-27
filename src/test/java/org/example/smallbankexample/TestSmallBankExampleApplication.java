package org.example.smallbankexample;

import org.springframework.boot.SpringApplication;

public class TestSmallBankExampleApplication {

    public static void main(String[] args) {
        SpringApplication.from(SmallBankExampleApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
