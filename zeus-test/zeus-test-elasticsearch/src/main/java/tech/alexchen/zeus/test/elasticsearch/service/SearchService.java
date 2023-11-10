package tech.alexchen.zeus.test.elasticsearch.service;

import tech.alexchen.zeus.test.elasticsearch.entity.SearchResult;

import java.util.List;

/**
 * @author alexchen
 */
public interface SearchService {

    /**
     * 根据输入返回相关的提示词
     * @param words 查询词
     * @return 结果
     */
    public List<SearchResult> prompt(String words);

    /**
     * 根据输入返回搜索结果
     * @param words 查询词
     * @return 结果
     */
    public List<SearchResult> search(String words);
}
