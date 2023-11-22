package tech.alexchen.zeus.test.common.web.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tech.alexchen.zeus.test.common.web.pojo.Role;

import java.util.List;

/**
 * @author alexchen
 */
@Component
public class RoleConverter implements Converter<String, Role> {

    @Override
    public Role convert(String source) {
        System.out.println("调用了 RoleConverter");
        List<String> split = StrUtil.split(source, ':');
        Role role = new Role();
        role.setId(Integer.valueOf(split.get(1)));
        return role;
    }

}
