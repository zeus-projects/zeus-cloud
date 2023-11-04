package tech.alexchen.zeus.test.consumer.controller;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author alexchen
 */
@Component
public class KafkaConsumerController {

    @KafkaListener(topics = {"alarm"})
    public void consumerMessage(ConsumerRecord<String, Object> record) {
        String info = StrUtil.format("消费消息: {}-{}={}", record.topic(), record.partition(), record.value());
        System.out.println(info);
    }
}
