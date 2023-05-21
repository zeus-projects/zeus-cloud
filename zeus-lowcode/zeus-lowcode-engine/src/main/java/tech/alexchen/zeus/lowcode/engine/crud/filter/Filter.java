package tech.alexchen.zeus.lowcode.engine.crud.filter;

/**
 * @author alexchen
 * @date 2023/3/5
 */
public interface Filter {

    /**
     * 执行过滤
     *
     * @param crudRequest 请求信息
     * @param crudResponse 响应信息
     * @param chain 过滤器链
     */
    void doFilter(CrudRequest crudRequest, CrudResponse crudResponse, FilterChain chain);
}
