package tech.alexchen.zeus.test.data.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author alexchen
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZeusTestDataMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusTestDataMybatisApplication.class, args);
    }

}
