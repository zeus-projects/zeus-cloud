package tech.alexchen.zeus.upms.api.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;

/**
 * @author alexchen
 */
@Component
@FeignClient(contextId = "remoteSysUserService", value = "zeus-upms-biz")
public interface RemoteSysUserService {

    /**
     * 根据用户名查询用户授权信息
     */
    @GetMapping(value = "/user/auth/{username}", headers = {"inner=true"})
    R<SysUserAuthDTO> getUserAuthInfo(@PathVariable(value = "username") String username);

}
