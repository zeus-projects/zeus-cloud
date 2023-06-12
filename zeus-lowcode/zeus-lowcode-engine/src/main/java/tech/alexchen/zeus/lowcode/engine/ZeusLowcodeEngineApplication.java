package tech.alexchen.zeus.lowcode.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tech.alexchen.zeus.common.feign.annotation.EnableZeusFeignClient;

/**
 * @author alexchen
 */

@EnableZeusFeignClient
@EnableDiscoveryClient
@SpringBootApplication
public class ZeusLowcodeEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusLowcodeEngineApplication.class, args);
    }
}
