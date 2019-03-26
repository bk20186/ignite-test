package com.gridfore.grip.web.grafana;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppConfiguration.class)
@SpringBootApplication
public class GrafanaAppStub {
    public static void main(String[] args) {
        SpringApplication.run(GrafanaAppStub.class, args);
    }
}
