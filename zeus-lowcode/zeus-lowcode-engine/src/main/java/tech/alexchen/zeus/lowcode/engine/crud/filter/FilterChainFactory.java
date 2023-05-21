package tech.alexchen.zeus.lowcode.engine.crud.filter;

import lombok.experimental.UtilityClass;
import tech.alexchen.zeus.lowcode.engine.crud.filter.impl.SensitiveFilter;
import tech.alexchen.zeus.lowcode.engine.crud.filter.impl.UrlFilter;

/**
 * @author alexchen
 * @date 2023/3/5
 */
@UtilityClass
public class FilterChainFactory {

    private static FilterChain filterChain;

    public static FilterChain getSingletonFilterChain() {
        if (filterChain == null) {
            synchronized (FilterChainFactory.class) {
                if (filterChain == null) {
                    filterChain = createFilterChain();
                }
                return filterChain;
            }
        } else {
            return filterChain;
        }
    }
    public static FilterChain createFilterChain() {
        FilterChain chain = new FilterChain();
        chain.add(new UrlFilter());
        chain.add(new SensitiveFilter());
        return chain;
    }
}
