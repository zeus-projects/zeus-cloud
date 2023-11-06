package tech.alexchen.zeus.test.producer.kafka;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author alexchen
 */
@Configuration
@EnableScheduling
public class KafkaProducerTask {

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

    protected Integer partition = 0;
    protected Integer count = 10;
    @Scheduled(cron = "0/1 * * * * ?")
    public void single() {
        for (int i = 0; i < count; i++) {
            KafkaMessage message = new KafkaMessage();
            message.setTopic("task-topic");
            message.setPartition(partition % 3);
            message.setKey(String.valueOf(partition));
            message.setMessage(LocalDateTime.now().toString());

            kafkaTemplate.send(message.getTopic(),
                    message.getPartition(),
                    message.getKey(),
                    message.getMessage()
            ).addCallback(futureCallback);
            partition++;
        }
    }

}
