package tech.alexchen.zeus.test.producer.kafka;

import lombok.Data;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class KafkaMessage implements Serializable {

    private String topic;

    private Integer partition;

    private String key;

    private Object message;

}
