package org.device.management.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.vaadin.spring.annotation.EnableVaadin;


@SpringBootApplication(scanBasePackages="org.device.management")
@EnableVaadin
@EnableAutoConfiguration
@ComponentScan(basePackages="org.device.management")
public class DeviceManagementApp {

    public static void main(final String[] args) {
        SpringApplication.run(DeviceManagementApp.class, args);
    }

    

}