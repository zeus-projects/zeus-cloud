package tech.alexchen.zeus.upms.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * VO、DO 转换器接口
 *
 * @author alexchen
 */
public interface BaseConvert<T, S, U, R> {

    /**
     * SaveVO 转 DO
     * @param saveVO
     * @return DO
     */
    T convertFromSave(S saveVO);

    /**
     * UpdateVO 转 DO
     * @param updateVO
     * @return DO
     */
    T convertFromUpdate(U updateVO);

    /**
     * DO 转 ResponseVO
     * @param entity
     * @return ResponseVO
     */
    R convertResponse(T entity);

    /**
     * 分页 DO 转 分页 ResponseVO
     * @param page
     * @return Page&lt;DO&gt;
     */
    Page<R> convertPage(Page<T> page);

    /**
     * 列表 DO 转 分页 列表
     * @param list
     * @return List&lt;DO&gt;
     */
    List<R> convertList(List<T> list);
}
