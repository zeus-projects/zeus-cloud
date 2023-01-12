package tech.alexchen.zeus.upms.controller.admin.tenant;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.starter.response.R;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantSaveVO;

/**
 * @author alexchen
 */
@Api(tags = "系统管理 - 租户")
@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class TenantController {

    @PostMapping
    public R<Long> save(TenantSaveVO vo) {
        return R.ok();
    }
}
