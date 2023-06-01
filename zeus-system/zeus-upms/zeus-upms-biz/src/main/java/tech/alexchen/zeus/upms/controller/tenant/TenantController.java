package tech.alexchen.zeus.upms.controller.tenant;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.enums.CommonStatusEnum;
import tech.alexchen.zeus.common.response.R;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantRequestVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantResponseVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantSaveVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.tenant.TenantUpdateVO;
import tech.alexchen.zeus.upms.convert.tenant.TenantConverter;
import tech.alexchen.zeus.upms.domain.tenant.TenantDO;
import tech.alexchen.zeus.upms.service.tenant.TenantService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author alexchen
 */
@Api(tags = "系统管理 - 租户")
@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;
    
    @PostMapping
    @ApiOperation("创建租户")
    public R<Long> save(@Valid @RequestBody TenantSaveVO vo) {
        TenantDO tenantDO = TenantConverter.INSTANCE.convertFromSave(vo);
        tenantService.save(tenantDO);
        return R.ok(tenantDO.getId());
    }

    @PutMapping
    @ApiOperation("更新租户套餐")
    public R<Boolean> update(@Valid @RequestBody TenantUpdateVO updateVO) {
        TenantDO Tenant = TenantConverter.INSTANCE.convertFromUpdate(updateVO);
        return R.ok(tenantService.updateById(Tenant));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除租户")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(tenantService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询单个租户")
    public R<TenantResponseVO> getById(@PathVariable Long id) {
        TenantDO tenant = tenantService.getById(id);
        return R.ok(TenantConverter.INSTANCE.convertToResponse(tenant));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询租户")
    public R<Page<TenantResponseVO>> page(Page page, TenantRequestVO param) {
        Page<TenantDO> pageRes = tenantService.page(page, param);
        return R.ok(TenantConverter.INSTANCE.convertToPage(pageRes));
    }

    @GetMapping("/list")
    @ApiOperation("列表查询租户")
    public R<List<TenantResponseVO>> list() {
        // 只查询状态
        List<TenantDO> listRes = tenantService.list(Wrappers.<TenantDO>lambdaQuery().eq(TenantDO::getStatus, CommonStatusEnum.ENABLE.getStatus()));
        return R.ok(TenantConverter.INSTANCE.convertToList(listRes));
    }

}
