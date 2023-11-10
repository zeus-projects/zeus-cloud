package tech.alexchen.zeus.test.kafka.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author alexchen
 */
@FeignClient(name = "zeus-test-producer", contextId="hello")
public interface ProducerFeign {

    @GetMapping("/hello")
    String hello();
}
