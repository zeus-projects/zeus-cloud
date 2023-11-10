package tech.alexchen.zeus.test.seata.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusTestSeataStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestSeataStockApplication.class, args);
    }

}