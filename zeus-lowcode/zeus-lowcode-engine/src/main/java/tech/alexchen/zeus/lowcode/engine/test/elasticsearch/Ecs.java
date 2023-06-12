package tech.alexchen.zeus.lowcode.engine.test.elasticsearch;

import lombok.Builder;
import lombok.Data;
//import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * @author alexchen
 */
@Data
@Builder
//@Document(indexName = "lowcode_ecs")
public class Ecs {

//    @Id
    private Long id;

    private String name;

    private Integer cpuCore;

    private Integer memory;

    private String ipv4;

    LocalDateTime createTime;
}
