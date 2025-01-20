package tech.alexchen.zeus.test.data.mybatis.controller;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.common.core.response.R;

/**
 * @author alexchen
 * @since 2025-01-20 15:06
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public R<String> getUserName() {
        if (jdbcTemplate != null) {
            return R.ok("yes");
        }
        return R.fail("no");
    }
}
