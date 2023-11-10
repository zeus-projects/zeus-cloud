package tech.alexchen.zeus.test.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusTestRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestRedisApplication.class, args);
    }

}
