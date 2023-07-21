package tech.alexchen.zeus.common.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class DefaultExceptionHandler {

    @Value("${spring.application.name}")
    private String applicationName;
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> handleException(Exception e) {
        log.error("返回异常服务: {}, exception: {}", applicationName, e.getMessage(), e);
        return R.fail(e.getLocalizedMessage());
    }


}
