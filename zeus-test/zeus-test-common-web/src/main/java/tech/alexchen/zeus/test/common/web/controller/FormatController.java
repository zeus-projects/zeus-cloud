package tech.alexchen.zeus.test.common.web.controller;

import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.test.common.web.pojo.UserDTO;

/**
 * @author alexchen
 */
@RestController
@RequestMapping("/format")
public class FormatController {


    /**
     * 时间格式转换测试
     */
    @PostMapping("/param")
    public UserDTO param(UserDTO dto) {
        System.out.println(JSONUtil.toJsonStr(dto));
        return dto;
    }

    /**
     * 时间格式转换测试
     */
    @PostMapping("/body")
    public UserDTO body(@RequestBody UserDTO dto) {
        System.out.println(JSONUtil.toJsonStr(dto));
        return dto;
    }

    /**
     * 测试自定义 convert
     */
    @PostMapping("/convert")
    public UserDTO convert(UserDTO dto) {
        System.out.println(JSONUtil.toJsonStr(dto));
        return dto;
    }
}
