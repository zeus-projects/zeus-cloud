package tech.alexchen.zeus.test.producer.seata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author alexchen
 */
@Mapper
@Repository
public interface StockMapper extends BaseMapper<Stock> {

}