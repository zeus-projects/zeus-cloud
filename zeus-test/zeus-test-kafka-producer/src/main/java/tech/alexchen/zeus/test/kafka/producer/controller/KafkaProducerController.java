package tech.alexchen.zeus.test.kafka.producer.controller;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.test.kafka.producer.entity.KafkaMessage;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ListenableFutureCallback<SendResult<String, String>> futureCallback = new ListenableFutureCallback<SendResult<String, String>>() {
        @Override
        public void onSuccess(SendResult<String, String> result) {
            assert result != null;
            ProducerRecord<String, String> record = result.getProducerRecord();
            System.out.println(StrUtil.format("Send message-> topic: {}, partition: {}, key: {}, value: {}", record.topic(), record.partition(), record.key(), record.value()));
        }

        @Override
        public void onFailure(Throwable e) {
            System.out.println(StrUtil.format("kafka Send message failed: {}", e.getMessage()));
        }
    };

    /**
     * 推送单条
     */
    @PostMapping("/send")
    public R<Boolean> sendMessage(@RequestBody KafkaMessage record) {
        kafkaTemplate.send(record.getTopic(), record.getPartition(), record.getKey(), record.getValue()).addCallback(futureCallback);
        return R.ok(true);
    }

    /**
     * 推送多条
     */
    @PostMapping("/send/batch")
    public R<Boolean> sendMessageBatch(@RequestBody List<KafkaMessage> records) {
        records.forEach(record -> kafkaTemplate.send(record.getTopic(), record.getPartition(), record.getKey(), record.getValue()).addCallback(futureCallback));
        return R.ok(true);
    }

}
