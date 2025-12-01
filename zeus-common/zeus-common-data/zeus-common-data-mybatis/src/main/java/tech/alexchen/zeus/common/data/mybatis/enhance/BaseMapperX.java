package tech.alexchen.zeus.common.data.mybatis.enhance;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageParam;
import tech.alexchen.zeus.common.data.mybatis.pojo.PageResult;

/**
 * @author alexchen
 */
public interface BaseMapperX<T> extends BaseMapper<T> {

    /**
     * 使用两个额外的 page 参数对象，主要是为了导出的 swagger 接口文档没有多余的参数
     */
    default PageResult<T> selectPage(PageParam pageParam, @Param("ew") Wrapper<T> queryWrapper) {
        IPage<T> mpPage = new Page<T>();
        mpPage.setSize(pageParam.getSize());
        mpPage.setCurrent(pageParam.getCurrent());
        selectPage(mpPage, queryWrapper);
        return new PageResult<>(mpPage.getSize(), mpPage.getCurrent(),
                mpPage.getTotal(), mpPage.getPages(), mpPage.getRecords());
    }

    /**
     * 为了 getByName 场景
     */
    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default T selectOne(Boolean condition, SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(condition, field, value));
    }
}
