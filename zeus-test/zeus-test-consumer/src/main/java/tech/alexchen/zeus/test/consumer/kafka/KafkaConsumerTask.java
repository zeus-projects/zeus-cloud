package tech.alexchen.zeus.test.consumer.kafka;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author alexchen
 */
@Component
public class KafkaConsumerTask {

    /**
     * kafka 单条消费
     */
    @KafkaListener(topics = {"alarm"})
    public void onNormalMessage(ConsumerRecord<String, Object> record) {
        String info = StrUtil.format("Kafka message: [{}-{}] {}: {}",
                record.topic(), record.partition(), record.key(), record.value());
        System.out.println(info);
    }

    /**
     * kafka 批量消费
     */
    @KafkaListener(topics = {"task-topic"})
    public void onBatchMessage(List<ConsumerRecord<String, Object>> records) {
        System.out.println(">>> Kafka batch message，size: " + records.size());
        for (ConsumerRecord<String, Object> record : records) {
            System.out.println(StrUtil.format("[{}-{}] {}: {}",
                    record.topic(), record.partition(), record.key(), record.value()));
        }
    }
}
