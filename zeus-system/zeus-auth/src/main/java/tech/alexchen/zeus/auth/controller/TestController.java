package tech.alexchen.zeus.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexchen
 * @date 2023/2/17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/dept")
    public String getDept() {
        return "Get Dept";
    }

    @GetMapping("/dept/save")
    public String saveDept() {
        return "Save Dept";
    }

    @GetMapping("/dept/update")
    public String updateDept() {
        return "Update Dept";
    }

}
