package tech.alexchen.zeus.upms.service.tenant;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tech.alexchen.zeus.upms.controller.tenant.vo.type.TenantPlanSaveVO;
import tech.alexchen.zeus.upms.entity.tenant.TenantPlanDO;

import javax.annotation.Resource;
import java.util.HashSet;

/**
 * @author alexchen
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class TenantPlanServiceTest {

    @Resource
    TenantPlanService service;

    static Long key;
    static TenantPlanDO plan;
    @Test
    @Order(1)
    void save() {
        TenantPlanSaveVO saveVO = new TenantPlanSaveVO();
        saveVO.setName("默认租户套餐");
        HashSet<Long> menuIds = new HashSet<>();
        menuIds.add(1l);
        menuIds.add(2l);
        menuIds.add(3l);
        saveVO.setMenuIds(menuIds);
        saveVO.setStatus(1);
        Long id = service.saveTenantPlan(saveVO);
        Assertions.assertNotNull(id);
        key = id;
    }

    @Test
    @Order(2)
    void query() {
        TenantPlanDO tenantPlanDO = service.getById(key);
        Assertions.assertNotNull(tenantPlanDO);
        plan = tenantPlanDO;
    }

    @Test
    @Order(3)
    void update() {
        TenantPlanDO planDO = plan;
        planDO.setStatus(0);
        Assertions.assertTrue(service.updateById(planDO));
    }

    @Test
    @Order(4)
    void delete() {
        Assertions.assertTrue(service.removeById(key));
    }
}