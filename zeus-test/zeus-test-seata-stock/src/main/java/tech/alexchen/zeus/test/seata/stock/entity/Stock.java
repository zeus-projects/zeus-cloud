package tech.alexchen.zeus.test.seata.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author alexchen
 */
@Data
@Accessors(chain = true)
@TableName("stock_tbl")
public class Stock {

    private Long id;
    private String commodityCode;
    private Long count;

}
