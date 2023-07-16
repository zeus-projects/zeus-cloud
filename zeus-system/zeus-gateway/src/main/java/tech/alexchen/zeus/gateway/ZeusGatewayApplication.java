package tech.alexchen.zeus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 * @date 2023/3/26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class  ZeusGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusGatewayApplication.class, args);
    }
}
