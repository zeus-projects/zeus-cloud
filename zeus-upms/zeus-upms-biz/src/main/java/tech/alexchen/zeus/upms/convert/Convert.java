package tech.alexchen.zeus.upms.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author alexchen
 */
public interface Convert<T, S, U, R> {

    T convertSave(S saveVO);

    T convertUpdate(U updateVO);

    R convertResponse(T entity);

    Page<R> convertPage(Page<T> page);

    List<R> convertList(List<T> list);
}
