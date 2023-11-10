package tech.alexchen.zeus.test.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusTestElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestElasticsearchApplication.class, args);
    }

}
