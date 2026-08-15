package com.roochi.petflowapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author farzane.rahmani
 * @created 8/7/2026
 */

@EnableScheduling
@SpringBootApplication
public class PetFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                PetFlowApplication.class,
                args
        );
    }
}
