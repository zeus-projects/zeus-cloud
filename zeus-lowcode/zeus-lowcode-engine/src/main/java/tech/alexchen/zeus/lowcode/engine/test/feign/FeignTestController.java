package tech.alexchen.zeus.lowcode.engine.test.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.upms.api.interfaces.UserFeign;

import javax.annotation.Resource;

/**
 * @author alexchen
 */
@RestController
public class FeignTestController {

    @Resource
    private UserFeign userFeign;;

    @GetMapping("/feign")
    public String feign() {
        return userFeign.test();
    }
}
