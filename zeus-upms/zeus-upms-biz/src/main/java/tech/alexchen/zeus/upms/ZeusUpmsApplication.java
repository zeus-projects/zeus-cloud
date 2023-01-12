package tech.alexchen.zeus.upms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.alexchen.zeus.starter.swagger.annotation.EnableZeusSwagger;

/**
 * @author alexchen
 */
@MapperScan("tech.alexchen.zeus.upms.dal.mapper")
@EnableZeusSwagger
@SpringBootApplication
public class ZeusUpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusUpmsApplication.class, args);
    }
}
