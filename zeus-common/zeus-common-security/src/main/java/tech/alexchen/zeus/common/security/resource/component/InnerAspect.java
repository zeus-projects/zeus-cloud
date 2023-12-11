package tech.alexchen.zeus.common.security.resource.component;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import tech.alexchen.zeus.common.security.core.SecurityConstants;
import tech.alexchen.zeus.common.security.core.SecurityUtil;
import tech.alexchen.zeus.common.security.resource.annotation.Inner;

/**
 * {@link Inner} 注解切面，用于判断被 {@link Inner} 注解标注的方法，是否可被外部范围；不可访问时，拒绝访问并抛出 {@link AccessDeniedException}
 *
 * @author alexchen
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class InnerAspect {

    @SneakyThrows
    @Before("@within(inner) || @annotation(inner)")
    public void around(JoinPoint point, Inner inner) {
        log.info("Invoking InnerAspect");
        if (inner == null) {
            Class<?> clazz = point.getTarget().getClass();
            inner = AnnotationUtils.findAnnotation(clazz, Inner.class);
        }
        boolean isInner = SecurityUtil.getInnerValue();
        log.debug("header inner value: {}", isInner);
        assert inner != null;
        if (inner.value() && !isInner) {
            log.warn("没有访问接口 {} 的权限", point.getSignature().getName());
            throw new AccessDeniedException("Access is denied");
        }
    }
}
