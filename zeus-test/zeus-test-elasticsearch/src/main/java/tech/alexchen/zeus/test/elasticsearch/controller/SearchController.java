package tech.alexchen.zeus.test.elasticsearch.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.test.elasticsearch.entity.SearchResult;
import tech.alexchen.zeus.test.elasticsearch.service.SearchService;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 模拟实现一个搜索功能，包含：
 * 1. 根据输入返回相关的提示词
 * 2. 根据输入返回搜索结果
 *
 * @author alexchen
 */
@RestController
@RequestMapping("/es")
public class SearchController {

    @Resource
    private SearchService searchService;

    @GetMapping("/prompt")
    public R<List<SearchResult>> prompt(String words) {
        if (StrUtil.isBlank(words)) {
            return R.ok(CollectionUtil.newArrayList());
        }
        List<tech.alexchen.zeus.test.elasticsearch.entity.SearchResult> results = searchService.prompt(words);
        return R.ok(results);
    }

    @GetMapping("/search")
    public R<List<SearchResult>> search(@NotBlank(message = "搜索输入为空错误") String words) {
        List<SearchResult> results = searchService.search(words);
        return R.ok(results);
    }
}
