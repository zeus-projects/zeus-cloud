package tech.alexchen.zeus.test.redis.comment;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.core.response.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现一个最新评论缓存，可以获取单个视频下最新的 n 条评论，使用 redis 的 list 实现
 *
 * @author alexchen
 */
@RestController
@RequestMapping("/redis/comment")
public class CommentController {

    @Resource
    private StringRedisTemplate template;

    private static final Integer RANGE = 10;

    @PostMapping
    public R<Boolean> save(@RequestBody Comment comment) {
        comment.setId(IdUtil.getSnowflakeNextId());
        comment.setTime(LocalDateTime.now());

        ListOperations<String, String> listOperations = template.opsForList();
        // 添加到队列中
        listOperations.leftPush(comment.getVideoId(), JSONUtil.toJsonStr(comment));
        // 仅保留最新的 n 条评论
        listOperations.trim(comment.getVideoId(), 0 , RANGE -1);
        return R.ok(true);
    }

    @GetMapping
    public R<List<Comment>> latestComments(@RequestParam(value = "videoId") String videoId) {
        ListOperations<String, String> listOperations = template.opsForList();
        List<String> list = listOperations.range(videoId, 0, RANGE - 1);
        if (CollUtil.isEmpty(list)) {
            return R.ok(new ArrayList<>());
        }
        List<Comment> comments = list.stream()
                .map(i -> JSONUtil.toBean(i, Comment.class))
                .collect(Collectors.toList());
        return R.ok(comments);
    }

}
