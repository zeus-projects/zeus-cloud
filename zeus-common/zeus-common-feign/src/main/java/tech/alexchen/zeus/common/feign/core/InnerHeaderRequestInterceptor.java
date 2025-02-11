package tech.alexchen.zeus.common.feign.core;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.core.Ordered;
import tech.alexchen.zeus.common.core.constants.CommonConstant;
import tech.alexchen.zeus.common.feign.annotation.InnerHeader;

import java.lang.reflect.Method;

/**
 * @author alexchen
 * @since 2025-02-11 10:12
 */
public class InnerHeaderRequestInterceptor implements RequestInterceptor, Ordered {

	@Override
	public void apply(RequestTemplate template) {
		Method method = template.methodMetadata().method();
		InnerHeader annotation = method.getAnnotation(InnerHeader.class);
		if (annotation != null) {
			template.header(CommonConstant.INNER_HEADER, CommonConstant.INNER_HEADER_ENABLE);
		}
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}
