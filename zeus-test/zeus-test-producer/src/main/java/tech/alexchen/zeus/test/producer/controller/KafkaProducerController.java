package tech.alexchen.zeus.test.producer.controller;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.test.producer.entity.KafkaMessage;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final ListenableFutureCallback<SendResult<String, Object>> futureCallback = new ListenableFutureCallback<SendResult<String, Object>>() {
        @Override
        public void onSuccess(SendResult<String, Object> result) {
            assert result != null;
            RecordMetadata metadata = result.getRecordMetadata();
            System.out.println(StrUtil.format("发送消息成功: {}-{}-{}", metadata.topic(), metadata.partition(), metadata.offset()));
        }
        @Override
        public void onFailure(Throwable e) {
            System.out.println(StrUtil.format("发送消息失败: {}", e.getMessage()));
        }
    };

    @PostMapping("/send")
    public R sendAlarmMessage(@RequestBody KafkaMessage message) {
        kafkaTemplate.send(message.getTopic(), message.getKey(), message.getMessage()).addCallback(futureCallback);
        return R.ok(true);
    }
}
