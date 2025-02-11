package tech.alexchen.zeus.common.core.constants;

/**
 * @author alexchen
 */
public interface CommonConstant {

    /**
     * 正常
     */
    Integer ENABLE = 0;

    /**
     * 停用
     */
    Integer DISABLE = 1;

    /**
     * 标识内部调用的请求头 key
     */
    String INNER_HEADER = "inner";

    /**
     * 内部调用启用请求头值
     */
    String INNER_HEADER_ENABLE = "true";

}
