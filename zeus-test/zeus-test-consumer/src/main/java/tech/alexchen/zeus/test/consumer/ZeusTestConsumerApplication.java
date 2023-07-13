package tech.alexchen.zeus.test.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusTestConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestConsumerApplication.class, args);
    }
}
