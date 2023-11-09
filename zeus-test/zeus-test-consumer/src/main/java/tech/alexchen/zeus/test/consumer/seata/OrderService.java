package tech.alexchen.zeus.test.consumer.seata;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author alexchen
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StockFeignClient stockFeignClient;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(
                orderMoney);
        // 本地操作
        orderMapper.insert(order);
        // 远程调用
        stockFeignClient.deduct(commodityCode, count);
    }

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder2(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(
                orderMoney);
        // 本地操作
        orderMapper.insert(order);
        // 远程调用
        stockFeignClient.deduct(commodityCode, count);
        // 本地异常
        throw new RuntimeException("模拟本地异常回滚");
    }

}
