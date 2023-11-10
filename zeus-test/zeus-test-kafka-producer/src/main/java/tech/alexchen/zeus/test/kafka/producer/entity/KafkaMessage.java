package tech.alexchen.zeus.test.kafka.producer.entity;

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

    private String value;

}
