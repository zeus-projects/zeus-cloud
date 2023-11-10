package tech.alexchen.zeus.test.seata.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.alexchen.zeus.common.core.response.R;

/**
 * 库存服务 FeignClient
 *
 * @author alexchen
 */
@FeignClient(name = "zeus-test-producer", contextId="seata-test")
public interface StockFeignClient {

    /**
     * 扣减库存
     */
    @GetMapping("/seata/stock/deduct")
    R<Boolean> deduct(@RequestParam("commodityCode") String commodityCode,
                      @RequestParam("count") Integer count);
}
