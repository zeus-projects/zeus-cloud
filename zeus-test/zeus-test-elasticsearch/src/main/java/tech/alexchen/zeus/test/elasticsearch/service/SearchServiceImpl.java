package tech.alexchen.zeus.test.elasticsearch.service;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import tech.alexchen.zeus.test.elasticsearch.entity.SearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexchen
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<SearchResult> prompt(String words) {
        List<SearchResult> results = new ArrayList<>();
        if (StrUtil.isNotBlank(words)) {
            // TODO 获取提示词列表
            System.out.println("获取提示词列表");
        }
        return results;
    }

    public List<SearchResult> search(String words) {
        List<SearchResult> results = new ArrayList<>();
        if (StrUtil.isNotBlank(words)) {
            // TODO 获取搜索结果
            System.out.println("获取搜索结果");
        }
        return results;
    }
}
