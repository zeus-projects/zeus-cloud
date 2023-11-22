package tech.alexchen.zeus.test.common.web.pojo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author alexchen
 */
@Data
@NoArgsConstructor
public class Role {

    private Integer id;

    public Role(String string) {
        List<String> split = StrUtil.split(string, ':');
        this.id = Integer.valueOf(split.get(1));
    }
}
