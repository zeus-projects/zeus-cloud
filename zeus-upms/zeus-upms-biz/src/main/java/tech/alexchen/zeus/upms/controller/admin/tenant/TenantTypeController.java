package tech.alexchen.zeus.upms.controller.admin.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.starter.apiversion.ApiVersion;
import tech.alexchen.zeus.starter.response.R;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypePageVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeResponseVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeSaveVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.TenantTypeUpdateVO;
import tech.alexchen.zeus.upms.convert.tenant.TenantTypeConvert;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantTypeDO;
import tech.alexchen.zeus.upms.service.admin.tenant.TenantTypeService;

/**
 * 租户类型管理接口
 *
 * @author alexchen
 */
@ApiVersion("1.0")
@RestController
@RequestMapping("/{v}/tenant-type")
@AllArgsConstructor
public class TenantTypeController {

    private final TenantTypeService tenantTypeService;

    @PostMapping
    public R<Long> saveTenantType(@RequestBody TenantTypeSaveVO addVO) {
        TenantTypeDO tenantType = TenantTypeConvert.INSTANCE.convert(addVO);
        tenantTypeService.save(tenantType);
        return R.ok(1L);
    }

    @GetMapping("/{id}")
    public R<TenantTypeResponseVO> getById(@PathVariable Long id) {
        TenantTypeDO tenantTypeDO = tenantTypeService.getById(id);
        TenantTypeResponseVO vo = TenantTypeConvert.INSTANCE.convert(tenantTypeDO);
        return R.ok(vo);
    }

    @GetMapping("/page")
    public R<Page<TenantTypeResponseVO>> page(Page page, TenantTypePageVO param) {
        return R.ok(tenantTypeService.page(page, param));
    }

    @PutMapping
    public R<Boolean> updateTenantType(@RequestBody TenantTypeUpdateVO updateVO) {
        TenantTypeDO tenantType = TenantTypeConvert.INSTANCE.convert(updateVO);
        return R.ok(tenantTypeService.updateById(tenantType));
    }

    @DeleteMapping("/{id}")
    public R<Boolean> removeTenantTypeById(@PathVariable Long id) {
        return R.ok(tenantTypeService.removeById(id));
    }


}
