package tech.alexchen.zeus.upms.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.common.feign.annotation.InnerHeader;
import tech.alexchen.zeus.common.feign.constants.FeignClientConstant;
import tech.alexchen.zeus.upms.api.dto.SysUserAuthDTO;

/**
 * @author alexchen
 */
@Component
@FeignClient(contextId = "remoteSysUserService", value = FeignClientConstant.UPMS)
public interface RemoteSysUserService {

    /**
     * 根据用户名查询用户授权信息
     */
    @InnerHeader
    @GetMapping(value = "/user/auth/username")
    R<SysUserAuthDTO> getUserAuthInfoByUsername(@RequestParam(value = "username") String username);

    /**
     * 更新最后登录信息-仅内部调用
     */
    @InnerHeader
    @PutMapping("/user/last-login")
    R<Boolean> updateLastLoginInfo(@RequestParam(value = "userId") Long userId);

}
