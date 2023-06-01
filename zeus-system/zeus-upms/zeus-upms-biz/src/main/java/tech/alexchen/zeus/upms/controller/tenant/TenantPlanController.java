package tech.alexchen.zeus.upms.controller.tenant;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.enums.CommonStatusEnum;
import tech.alexchen.zeus.common.response.R;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanRequestVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanResponseVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanSaveVO;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanUpdateVO;
import tech.alexchen.zeus.upms.convert.tenant.TenantPlanConverter;
import tech.alexchen.zeus.upms.domain.tenant.TenantPlanDO;
import tech.alexchen.zeus.upms.service.tenant.TenantPlanService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 租户套餐管理接口
 *
 * @author alexchen
 */
@Api(tags = "系统管理 - 租户套餐")
@RestController
@RequestMapping("/tenant-plan")
public class TenantPlanController {

    @Resource
    TenantPlanService service;

    @Resource
    TenantPlanConverter converter;

    @PostMapping
    @ApiOperation("创建租户套餐")
    public R<Long> save(@Valid @RequestBody TenantPlanSaveVO addVO) {
        return R.ok(service.saveTenantPlan(addVO));
    }

    @PutMapping
    @ApiOperation("更新租户套餐")
    public R<Boolean> update(@Valid @RequestBody TenantPlanUpdateVO updateVO) {
        TenantPlanDO tenantPlan = converter.convertFromUpdate(updateVO);
        return R.ok(service.updateById(tenantPlan));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除租户套餐")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(service.removeById(id));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询单个租户套餐")
    public R<TenantPlanResponseVO> getById(@PathVariable Long id) {
        TenantPlanDO tenantPlanDO = service.getById(id);
        TenantPlanResponseVO vo = converter.convertToResponse(tenantPlanDO);
        return R.ok(vo);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询租户套餐")
    public R<Page<TenantPlanResponseVO>> page(Page page, TenantPlanRequestVO param) {
        Page<TenantPlanDO> pageRes = service.page(page, param);
        return R.ok(converter.convertToPage(pageRes));
    }

    @GetMapping("/list")
    @ApiOperation("列表查询租户套餐")
    public R<List<TenantPlanResponseVO>> list() {
        // 只查询状态
        List<TenantPlanDO> listRes = service.list(Wrappers.<TenantPlanDO>lambdaQuery().eq(TenantPlanDO::getStatus, CommonStatusEnum.ENABLE.getStatus()));
        return R.ok(converter.convertToList(listRes));
    }

}
