package tech.alexchen.zeus.test.consumer.kafka;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * listener 类型设置为 single 时，才加载此 Bean
 *
 * @author alexchen
 */
@Component
@ConditionalOnProperty(name = "spring.kafka.listener.type", havingValue = "single")
public class KafkaSingleConsumerTest {

    /**
     * kafka 单条消费
     */
    @KafkaListener(groupId = "single-test",topics = {"topic-single"})
    public void onSingleMessage(ConsumerRecord<String, Object> record) {
        System.out.println(StrUtil.format(">>> kafka single message, topic: {}, partition: {}, key: {}, value: {}",
                record.topic(), record.partition(), record.key(), record.value()));
    }
}
