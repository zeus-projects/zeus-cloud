package tech.alexchen.zeus.test.producer.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class KafkaMessage implements Serializable {

    private String topic;

    private String key;

    private Object message;

}
