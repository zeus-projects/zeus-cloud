package tech.alexchen.zeus.test.producer.seata;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@Service
public class StockService {

    @Resource
    private StockMapper stockMapper;

    /**
     * 减库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        if ("product-2".equals(commodityCode)) {
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }

        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Stock().setCommodityCode(commodityCode));
        Stock stock = stockMapper.selectOne(wrapper);
        stock.setCount(stock.getCount() - count);

        stockMapper.updateById(stock);
    }
}
