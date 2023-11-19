package tech.alexchen.zeus.common.data.mybatis.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alexchen
 */
@Schema
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class PageX<T> implements IPage<T> {

    /**
     * 每页显示条数，默认 10
     */
    @Schema(description = "每页显示条数", defaultValue = "10")
    protected long size = 10;

    /**
     * 当前页
     */
    @Schema(description = "当前页", defaultValue = "1")
    protected long current = 1;

    /**
     * 总数
     */
    @Schema(description = "总数")
    protected long total = 0;

    /**
     * 数据列表
     */
    @Schema(description = "数据列表")
    protected List<T> records = Collections.emptyList();

    /**
     * 排序字段信息
     */
    @Setter
    @Schema(description = "排序")
    protected List<OrderItem> orders = new ArrayList<>();

    @Override
    public List<OrderItem> orders() {
        return this.orders;
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public PageX<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public PageX<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public PageX<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public PageX<T> setCurrent(long current) {
        this.current = current;
        return this;
    }
}
