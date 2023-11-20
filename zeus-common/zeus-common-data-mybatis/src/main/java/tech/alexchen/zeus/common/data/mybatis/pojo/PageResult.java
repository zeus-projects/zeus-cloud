package tech.alexchen.zeus.common.data.mybatis.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author alexchen
 */
@Schema
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends PageParam implements Serializable {

    /**
     * 总数
     */
    @Schema(description = "总数")
    protected long total = 0;

    /**
     * 总页数
     */
    @Schema(description = "总页数")
    protected long pages;

    /**
     * 数据列表
     */
    @Schema(description = "数据列表")
    protected List<T> records = Collections.emptyList();

    public PageResult() {}

    public PageResult(long size, long current, long total, long pages, List<T> records) {
        super(size, current);
        this.total = total;
        this.pages = pages;
        this.records = records;
    }
}
