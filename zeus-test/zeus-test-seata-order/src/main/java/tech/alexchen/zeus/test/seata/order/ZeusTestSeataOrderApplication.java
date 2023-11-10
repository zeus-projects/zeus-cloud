package tech.alexchen.zeus.test.seata.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusTestSeataOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestSeataOrderApplication.class, args);
    }

}