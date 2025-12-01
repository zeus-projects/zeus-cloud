package tech.alexchen.zeus.common.data.mybatis.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author alexchen
 */
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam implements Serializable {

    /**
     * 每页显示条数
     */
    @Schema(description = "每页显示条数", defaultValue = "10")
    protected long size = 10;

    /**
     * 当前页
     */
    @Schema(description = "当前页", defaultValue = "1")
    protected long current = 1;

}
