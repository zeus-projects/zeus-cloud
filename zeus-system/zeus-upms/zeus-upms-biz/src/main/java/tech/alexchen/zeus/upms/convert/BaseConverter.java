package tech.alexchen.zeus.upms.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * VO、DO 转换器接口
 *
 * @author alexchen
 */
public interface BaseConverter<Entity, Save, Update, Response> {

    /**
     * SaveVO 转 DO
     * @param saveVO
     * @return DO
     */
    Entity convertFromSave(Save saveVO);

    /**
     * UpdateVO 转 DO
     * @param updateVO
     * @return DO
     */
    Entity convertFromUpdate(Update updateVO);

    /**
     * DO 转 ResponseVO
     * @param entity
     * @return ResponseVO
     */
    Response convertToResponse(Entity entity);

    /**
     * 分页 DO 转 分页 ResponseVO
     * @param page
     * @return Page&lt;DO&gt;
     */
    Page<Response> convertToPage(Page<Entity> page);

    /**
     * 列表 DO 转 分页 列表
     * @param list
     * @return List&lt;DO&gt;
     */
    List<Response> convertToList(List<Entity> list);
}
