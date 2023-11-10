package tech.alexchen.zeus.test.kafka.consumer.listener;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author alexchen
 */
@Component
@ConditionalOnProperty(name = "spring.kafka.consumer.enable-auto-commit", havingValue = "false")
public class KafkaConsumerAckModeTest {

    /**
     * ack 手动提交
     */
    @KafkaListener(groupId = "ack-mode-test", topics = {"topic-ack"})
    public void manualImmediate(List<ConsumerRecord<String, Object>> records, Acknowledgment ack) {
        System.out.println(">>> Kafka batch message，size: " + records.size());
        for (ConsumerRecord<String, Object> record : records) {
            System.out.println(StrUtil.format("topic: {}, partition: {}, key: {}, value: {}",
                    record.topic(), record.partition(), record.key(), record.value()));
        }
        // 手动确认：确认单当前消息（及之前的消息）offset均已被消费完成
        ack.acknowledge();
    }
}
