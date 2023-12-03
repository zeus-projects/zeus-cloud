package tech.alexchen.zeus.auth.endpoint;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexchen
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public Object hello() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
