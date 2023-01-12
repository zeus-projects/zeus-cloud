package tech.alexchen.zeus.upms.controller.admin.tenant;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexchen
 */
@Api(tags = "系统管理 - 租户")
@RestController
@RequestMapping("/{v}/tenant")
@RequiredArgsConstructor
public class TenantController {


}
