package tech.alexchen.zeus.common.data.mybatis.typehandler;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;

import java.util.Set;

/**
 * 参考 {@link com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler} 实现
 * 在我们将字符串反序列化为 Set 并且泛型为 Long 时，如果每个元素的数值太小，会被处理成 Integer 类型，导致可能存在隐性的 BUG。
 *
 * @author alexchen
 */
public class JsonLongSetTypeHandler extends AbstractJsonTypeHandler<Set<Long>> {

    private static final TypeReference<Set<Long>> TYPE_REFERENCE = new TypeReference<Set<Long>>(){};

    @Override
    protected Set<Long> parse(String json) {
        return JSONUtil.toBean(json, TYPE_REFERENCE, false);
    }

    @Override
    protected String toJson(Set<Long> obj) {
        return JSONUtil.toJsonStr(obj);
    }
}
