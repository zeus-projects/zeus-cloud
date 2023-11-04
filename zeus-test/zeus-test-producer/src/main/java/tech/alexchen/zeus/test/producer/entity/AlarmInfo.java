package tech.alexchen.zeus.test.producer.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Data
public class AlarmInfo implements Serializable {

    private String id;

    private String title;

    private Integer level;

    private String detail;

    private Long timestamp;
}
