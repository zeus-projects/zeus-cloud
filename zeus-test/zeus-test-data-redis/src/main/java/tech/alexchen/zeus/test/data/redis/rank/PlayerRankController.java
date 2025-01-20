package tech.alexchen.zeus.test.data.redis.rank;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;

import java.util.Collection;
import java.util.Set;

/**
 * 利用 redis 的 zset，实现一个排行榜功能
 *
 * @author alexchen
 */
@RestController
@RequestMapping("/redis/rank")
public class PlayerRankController {

    @Resource
    private StringRedisTemplate template;

    private static final String KEY = "leaderboard";

    @PostMapping
    public R<Boolean> save(@RequestBody Player player) {
        ZSetOperations<String, String> operations = template.opsForZSet();
        operations.add(KEY, player.getUsername(), player.getScore());
        return R.ok(true);
    }

    @GetMapping
    public R<Long> rank(@RequestParam(value = "username") String username) {
        ZSetOperations<String, String> operations = template.opsForZSet();
        Long rank = operations.reverseRank(KEY, username);
        return R.ok(rank);
    }

    @GetMapping("/top")
    public R<Collection<String>> topN(@RequestParam(value = "n") Long n) {
        ZSetOperations<String, String> operations = template.opsForZSet();
        // 获取前 5 名
        Set<String> range = operations.reverseRange(KEY, 0, n);
        return R.ok(range);
    }

}
