package tech.alexchen.zeus.test.seata.stock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.test.seata.stock.service.StockService;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/seata/stock")
public class StockController {

    @Resource
    private StockService stockService;

    /**
     * 减库存
     *
     * @param commodityCode 商品代码
     * @param count         数量
     */
    @GetMapping(path = "/deduct")
    public R<Boolean> deduct(String commodityCode, Integer count) {
        stockService.deduct(commodityCode, count);
        return R.ok(true);
    }

}
