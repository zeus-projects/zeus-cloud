package tech.alexchen.zeus.test.producer.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * kafka 自动创建 topic
 *
 * @author alexchen
 */
@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic topicSingle() {
        return new NewTopic("topic-single",3, (short) 1);
    }

    @Bean
    public NewTopic topicBatch() {
        return new NewTopic("topic-batch",3, (short) 1);
    }
}
