package tech.alexchen.zeus.test.seata.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.test.seata.order.service.OrderService;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/seata/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 下单：插入订单表、扣减库存，正常
     */
    @RequestMapping("/placeOrder/commit")
    public R<Boolean> placeOrderCommit() {
        orderService.placeOrder("1", "product-1", 1);
        return R.ok(true);
    }

    /**
     * 下单：插入订单表、扣减库存，模拟远程调用异常回滚
     */
    @RequestMapping("/placeOrder/rollback/remote")
    public R<Boolean> placeOrderRollback1() {
        // product-2 扣库存时模拟了一个业务异常
        orderService.placeOrder("1", "product-2", 1);
        return R.ok(true);
    }

    /**
     * 下单：插入订单表、扣减库存，模拟本地服务异常回滚
     */
    @RequestMapping("/placeOrder/rollback/local")
    public R<Boolean> placeOrderRollback2() {
        // 主动抛出一个异常，让远程服务回滚
        orderService.placeOrder2("1", "product-1", 1);
        return R.ok(true);
    }
}
