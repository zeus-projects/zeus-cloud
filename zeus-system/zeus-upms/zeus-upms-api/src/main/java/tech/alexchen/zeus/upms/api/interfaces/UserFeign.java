package tech.alexchen.zeus.upms.api.interfaces;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author alexchen
 */
@FeignClient(name = "zeus-upms-biz")
public interface UserFeign {


}
