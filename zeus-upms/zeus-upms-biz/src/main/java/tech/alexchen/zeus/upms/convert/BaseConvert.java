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
     */
    T convertSave(S saveVO);

    /**
     * UpdateVO 转 DO
     */
    T convertUpdate(U updateVO);

    /**
     * DO 转 ResponseVO
     */
    R convertResponse(T entity);

    /**
     * 分页 DO 转 分页 ResponseVO
     */
    Page<R> convertPage(Page<T> page);

    /**
     * 列表 DO 转 分页 列表
     */
    List<R> convertList(List<T> list);
}
