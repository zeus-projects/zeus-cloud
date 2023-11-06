package tech.alexchen.zeus.test.producer.kafka;

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
            ProducerRecord<String, Object> record = result.getProducerRecord();
            System.out.println(StrUtil.format("Send message-> {}-{} {}: {}",
                    record.topic(), record.partition(), record.key(), record.value()));
        }
        @Override
        public void onFailure(Throwable e) {
            System.out.println(StrUtil.format("Send message failed: {}", e.getMessage()));
        }
    };

    @PostMapping("/send")
    public R<Boolean> sendAlarmMessage(@RequestBody KafkaMessage message) {
        kafkaTemplate.send(message.getTopic(),
                message.getPartition(),
                message.getKey(),
                message.getMessage()
        ).addCallback(futureCallback);
        return R.ok(true);
    }
}
