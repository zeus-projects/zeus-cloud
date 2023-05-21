package tech.alexchen.zeus.lowcode.engine.crud.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * FilterChain 也实现了 Filter，可以让不同的 FilterChain 连接在一起
 *
 * @author alexchen
 * @date 2023/3/5
 */
@Slf4j
public class FilterChain {

    private List<Filter> filters = new CopyOnWriteArrayList();
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public FilterChain add(Filter filter) {
        if (filter == null) {
            throw new RuntimeException("Filter invalid");
        }
        filters.add(filter);
        return this;
    }

    public void doFilter(CrudRequest crudRequest, CrudResponse crudResponse) {
        int index = threadLocal.get();
        if (index >= filters.size()) {
            threadLocal.remove();
            return;
        }
        Filter filter = filters.get(index);
        threadLocal.set(++index);
        filter.doFilter(crudRequest, crudResponse, this);
    }
}
