package tech.alexchen.zeus.lowcode.engine.crud.filter.impl;

import lombok.extern.slf4j.Slf4j;
import tech.alexchen.zeus.lowcode.engine.crud.filter.CrudRequest;
import tech.alexchen.zeus.lowcode.engine.crud.filter.CrudResponse;
import tech.alexchen.zeus.lowcode.engine.crud.filter.Filter;
import tech.alexchen.zeus.lowcode.engine.crud.filter.FilterChain;

/**
 * @author alexchen
 * @date 2023/3/5
 */
@Slf4j
public class UrlFilter implements Filter {

    @Override
    public void doFilter(CrudRequest crudRequest, CrudResponse crudResponse, FilterChain chain) {
        chain.doFilter(crudRequest, crudResponse);
    }
}
