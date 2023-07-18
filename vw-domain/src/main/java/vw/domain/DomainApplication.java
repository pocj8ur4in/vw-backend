package vw.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomainApplication {
    public static void main(String[] args) {
        System.setProperty(
                "spring.config.name", "application-domain, application-core, application-infra");

        SpringApplication.run(DomainApplication.class, args);
    }
}
