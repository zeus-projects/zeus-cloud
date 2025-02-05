package tech.alexchen.zeus.common.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.alexchen.zeus.common.core.exception.ResponsiveRuntimeException;
import tech.alexchen.zeus.common.core.response.R;

import java.util.List;

/**
 * @author alexchen
 */
@Slf4j
@Order(-1000)
@RestControllerAdvice
public class WebMvcExceptionHandler {

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 捕获任意异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<String> handleException(Exception e) {
        log.error("服务 {} 发生异常, exception: {}", applicationName, e.getMessage(), e);
        return R.fail(e.getLocalizedMessage());
    }

    /**
     * 捕获非法参数异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数, exception: {}", e.getMessage(), e);
        return R.fail(e.getMessage());
    }

    /**
     * 捕获参数验证失败异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handleBodyValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        log.error("参数绑定异常, exception: {}", fieldErrors.get(0).getDefaultMessage());
        return R.fail(String.format("%s %s", fieldErrors.get(0).getField(), fieldErrors.get(0).getDefaultMessage()));
    }

    /**
     * 捕获参数绑定异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handleBodyValidException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        log.error("参数绑定异常, exception: {}", fieldErrors.get(0).getDefaultMessage());
        return R.fail(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return R
     */
    @ExceptionHandler({ResponsiveRuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T extends ResponsiveRuntimeException> R<String> handleServiceException(T e) {
        log.error("业务异常, Exception: {}", e.getErrorMessage());
        return R.build(e.getErrorCode(), e.getErrorMessage(), null);
    }

}
