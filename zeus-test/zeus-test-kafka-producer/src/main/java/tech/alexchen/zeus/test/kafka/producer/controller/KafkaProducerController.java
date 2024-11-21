package tech.alexchen.zeus.test.kafka.producer.controller;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.test.kafka.producer.entity.KafkaMessage;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private static void accept(SendResult<String, String> result, Throwable throwable) {
        if (throwable != null) {
            throw new RuntimeException(throwable);
        }
        assert result != null;
        ProducerRecord<String, String> producerRecord = result.getProducerRecord();
        System.out.println(StrUtil.format("Send message-> topic: {}, partition: {}, key: {}, value: {}", producerRecord.topic(), producerRecord.partition(), producerRecord.key(), producerRecord.value()));
    }

    /**
     * 推送单条
     */
    @PostMapping("/send")
    public R<Boolean> sendMessage(@RequestBody KafkaMessage record) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(record.getTopic(), record.getPartition(), record.getKey(), record.getValue());
        future.whenComplete(KafkaProducerController::accept);
        return R.ok(true);
    }

    /**
     * 推送多条
     */
    @PostMapping("/send/batch")
    public R<Boolean> sendMessageBatch(@RequestBody List<KafkaMessage> records) {
        records.forEach(record -> kafkaTemplate.send(record.getTopic(), record.getPartition(), record.getKey(), record.getValue())
                .whenComplete(KafkaProducerController::accept));
        return R.ok(true);
    }

}
