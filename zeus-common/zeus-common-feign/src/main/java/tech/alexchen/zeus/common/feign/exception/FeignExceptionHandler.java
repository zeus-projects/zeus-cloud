package tech.alexchen.zeus.common.feign.exception;

import cn.hutool.core.util.StrUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.alexchen.zeus.common.core.response.R;

/**
 * @author alexchen
 */
@Slf4j
@RestControllerAdvice
public class FeignExceptionHandler {

    /**
     * 远程调用异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler({FeignException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> handleFeignException(FeignException e) {
        log.error("远程调用异常, 请求路径: {}", e.request().url());
        return R.fail(StrUtil.format("远程调用异常, url: {}", e.request().url()));
    }

}
