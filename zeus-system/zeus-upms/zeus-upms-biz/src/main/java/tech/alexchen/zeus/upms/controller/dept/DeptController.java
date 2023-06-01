package tech.alexchen.zeus.upms.controller.dept;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.alexchen.zeus.common.response.R;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptRequestVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptResponseVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptSaveVO;
import tech.alexchen.zeus.upms.controller.dept.vo.DeptUpdateVO;
import tech.alexchen.zeus.upms.convert.dept.DeptConverter;
import tech.alexchen.zeus.upms.domain.dept.DeptDO;
import tech.alexchen.zeus.upms.service.dept.DeptService;

import javax.validation.Valid;
import java.util.List;


/**
 * 部门管理
 *
 * @author alexchen
 */
@Api(tags = "系统管理 - 部门")
@RestController
@AllArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @PostMapping
    @ApiOperation("创建部门")
    public R<Long> save(@Valid @RequestBody DeptSaveVO saveVO) {
        Long id = deptService.saveDept(saveVO);
        return R.ok(id);
    }

    @PutMapping
    @ApiOperation("更新部门")
    public R<Boolean> update(@Valid @RequestBody DeptUpdateVO updateVO) {
        return R.ok(deptService.updateDept(updateVO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除部门")
    public R<Boolean> removeById(@PathVariable Long id) {
        return R.ok(deptService.removeDept(id));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询单个部门")
    public R<DeptResponseVO> getById(@PathVariable Long id) {
        DeptDO dept = deptService.getById(id);
        return R.ok(DeptConverter.INSTANCE.convertToResponse(dept));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询部门")
    public R<Page<DeptResponseVO>> page(Page page, DeptRequestVO vo) {
        Page<DeptDO> pageRes = deptService.pageDept(page, vo);
        return R.ok(DeptConverter.INSTANCE.convertToPage(pageRes));
    }

    @GetMapping("/list")
    @ApiOperation("列表查询部门")
    public R<List<DeptResponseVO>> list() {
        return R.ok(DeptConverter.INSTANCE.convertToList(deptService.list()));
    }

}
