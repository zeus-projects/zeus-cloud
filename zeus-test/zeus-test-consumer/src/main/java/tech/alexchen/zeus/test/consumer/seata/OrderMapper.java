package tech.alexchen.zeus.test.consumer.seata;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author alexchen
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
