package tech.alexchen.zeus.test.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author alexchen
 */
@Data
@Builder
@AllArgsConstructor
@Document(indexName = "index_ecs")
public class Ecs {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Ip)
    private String ipv4;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String desc;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String dept;

}
