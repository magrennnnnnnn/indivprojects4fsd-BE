package com.prolink.prolink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.prolink.prolink")
public class ProLinkBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProLinkBeApplication.class, args);
    }
}