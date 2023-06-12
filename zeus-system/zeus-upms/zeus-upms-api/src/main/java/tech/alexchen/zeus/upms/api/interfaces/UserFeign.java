package tech.alexchen.zeus.upms.api.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.alexchen.zeus.common.core.response.R;
import tech.alexchen.zeus.upms.api.dto.UserDTO;

/**
 * @author alexchen
 */
@FeignClient(name = "zeus-upms-biz")
public interface UserFeign {

    @GetMapping("/test")
    String test();
    @GetMapping("/api/user/{id}")
    R<UserDTO> getUserById(@PathVariable(value = "id") Long id);

}
