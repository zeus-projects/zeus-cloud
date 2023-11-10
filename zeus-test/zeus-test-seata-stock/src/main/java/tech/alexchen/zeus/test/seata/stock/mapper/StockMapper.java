package tech.alexchen.zeus.test.seata.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tech.alexchen.zeus.test.seata.stock.entity.Stock;

/**
 * @author alexchen
 */
@Mapper
@Repository
public interface StockMapper extends BaseMapper<Stock> {

}