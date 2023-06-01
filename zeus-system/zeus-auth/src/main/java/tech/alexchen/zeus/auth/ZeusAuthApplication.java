package tech.alexchen.zeus.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusAuthApplication.class, args);
    }
}