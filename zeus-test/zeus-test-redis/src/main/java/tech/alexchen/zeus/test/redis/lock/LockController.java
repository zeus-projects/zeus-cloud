package tech.alexchen.zeus.test.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author alexchen
 */
@Slf4j
@RestController
@RequestMapping("/redis/lock")
public class LockController {

    @Resource
    private RedissonClient redissonClient;

    @PostMapping
    public R<Boolean> doSomething() throws InterruptedException {
        // 加锁
        RLock lock = redissonClient.getLock("lock");
        //加锁，参数：获取锁的最大等待时间（期间会重试），锁自动释放时间，时间单位
        //注意：如果指定锁自动释放时间，不管业务有没有执行完，锁都不会自动延期，即没有 watch dog 机制。
        boolean isLock = lock.tryLock(1, 5, TimeUnit.SECONDS);
        try {
            if (isLock) {
                log.info("获取分布式锁成功");
                Thread.sleep(3000);
                log.info("业务完成");
            } else {
                log.error("获取分布式锁失败");
                return R.fail("获取分布式锁失败，业务失败", false);
            }
        } catch (Exception e) {
            throw new RuntimeException("业务异常");
        } finally {
            //当前线程未解锁
            if (lock.isHeldByCurrentThread() && lock.isLocked()) {
                //释放锁
                log.info("释放锁");
                lock.unlock();
            }
        }
        return R.ok(true);
    }

}
