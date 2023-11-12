package tech.alexchen.zeus.test.seata.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tech.alexchen.zeus.common.feign.annotation.EnableZeusFeignClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZeusFeignClient
public class ZeusTestSeataOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestSeataOrderApplication.class, args);
    }

}