package tech.alexchen.zeus.test.seata.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tech.alexchen.zeus.test.seata.order.entity.Order;

/**
 * @author alexchen
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
