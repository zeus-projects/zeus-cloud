package tech.alexchen.zeus.upms.controller.admin.tenant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.alexchen.zeus.starter.response.R;

/**
 * 租户类型管理接口
 *
 * @author alexchen
 */
@RestController
@RequestMapping("/{v}/tenant-type")
public class SysTenantTypeController {

    public R<Boolean> addTenantType() {
        return R.ok();
    }
}
