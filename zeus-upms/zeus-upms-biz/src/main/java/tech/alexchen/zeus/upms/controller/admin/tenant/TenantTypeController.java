package tech.alexchen.zeus.upms.controller.admin.tenant;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.starter.enums.CommonStatusEnum;
import tech.alexchen.zeus.starter.response.R;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.type.TenantTypeRequestVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.type.TenantTypeResponseVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.type.TenantTypeSaveVO;
import tech.alexchen.zeus.upms.controller.admin.tenant.vo.type.TenantTypeUpdateVO;
import tech.alexchen.zeus.upms.convert.tenant.TenantTypeConvert;
import tech.alexchen.zeus.upms.dal.dataobject.tenant.TenantTypeDO;
import tech.alexchen.zeus.upms.service.admin.tenant.TenantTypeService;

import javax.validation.Valid;
import java.util.List;

/**
 * 租户类型管理接口
 *
 * @author alexchen
 */
@Api(tags = "系统管理 - 租户类型")
@RestController
@RequestMapping("/tenant-type")
@AllArgsConstructor
public class TenantTypeController {

    private final TenantTypeService tenantTypeService;

    @PostMapping
    @ApiOperation("创建租户类型")
    public R<Long> save(@Valid @RequestBody TenantTypeSaveVO addVO) {
        TenantTypeDO tenantType = TenantTypeConvert.INSTANCE.convertSave(addVO);
        tenantTypeService.save(tenantType);
        return R.ok(tenantType.getId());
    }

    @PutMapping
    @ApiOperation("更新租户类型")
    public R<Boolean> update(@Valid @RequestBody TenantTypeUpdateVO updateVO) {
        TenantTypeDO tenantType = TenantTypeConvert.INSTANCE.convertUpdate(updateVO);
        return R.ok(tenantTypeService.updateById(tenantType));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除租户类型")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(tenantTypeService.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询单个租户类型")
    public R<TenantTypeResponseVO> getById(@PathVariable Long id) {
        TenantTypeDO tenantTypeDO = tenantTypeService.getById(id);
        TenantTypeResponseVO vo = TenantTypeConvert.INSTANCE.convertResponse(tenantTypeDO);
        return R.ok(vo);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询租户类型")
    public R<Page<TenantTypeResponseVO>> page(Page page, TenantTypeRequestVO param) {
        Page<TenantTypeDO> pageRes = tenantTypeService.page(page, param);
        return R.ok(TenantTypeConvert.INSTANCE.convertPage(pageRes));
    }

    @GetMapping("/list")
    @ApiOperation("列表查询租户类型")
    public R<List<TenantTypeResponseVO>> list() {
        // 只查询状态
        List<TenantTypeDO> listRes = tenantTypeService.list(Wrappers.<TenantTypeDO>lambdaQuery().eq(TenantTypeDO::getStatus, CommonStatusEnum.ENABLE.getStatus()));
        return R.ok(TenantTypeConvert.INSTANCE.convertList(listRes));
    }

}
