package tech.alexchen.zeus.test.data.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusTestDataRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestDataRedisApplication.class, args);
    }

}
