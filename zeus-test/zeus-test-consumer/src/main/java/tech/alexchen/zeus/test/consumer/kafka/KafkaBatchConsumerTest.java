package tech.alexchen.zeus.test.consumer.kafka;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * listener 类型设置为 batch 时，才加载此 Bean
 *
 * @author alexchen
 */
@Component
@ConditionalOnProperty(name = "spring.kafka.listener.type", havingValue = "batch")
public class KafkaBatchConsumerTest {

    /**
     * kafka 批量消费
     */
    @KafkaListener(topics = {"topic-batch"})
    public void onBatchMessage(List<ConsumerRecord<String, Object>> records) {
        System.out.println(">>> Kafka batch message，size: " + records.size());
        for (ConsumerRecord<String, Object> record : records) {
            System.out.println(StrUtil.format("topic: {}, partition: {}, key: {}, value: {}",
                    record.topic(), record.partition(), record.key(), record.value()));
        }
    }
}
