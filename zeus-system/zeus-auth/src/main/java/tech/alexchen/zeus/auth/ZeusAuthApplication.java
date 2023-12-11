package tech.alexchen.zeus.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author alexchen
 */
@EnableFeignClients(basePackages = {"tech.alexchen.zeus"})
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusAuthApplication.class, args);
    }
}
