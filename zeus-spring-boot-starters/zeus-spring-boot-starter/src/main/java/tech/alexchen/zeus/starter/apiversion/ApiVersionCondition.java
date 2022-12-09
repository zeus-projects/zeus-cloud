package tech.alexchen.zeus.starter.apiversion;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author alexchen
 */
@Slf4j
public final class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v(\\d+\\.?\\d{0,2})/");

    @Getter
    private final String apiVersion;

    public ApiVersionCondition(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * method priority is higher then class.
     *
     * @param other other
     * @return ApiVersionCondition
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition(other.apiVersion);
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        log.info("RequestURI: {}", request.getRequestURI());
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (m.find()) {
            // 得到版本号的字符串
            String version = m.group(0).replace("/v", "").replace("/", "");
            if (compareVersion(version, this.apiVersion) == 0) {
                log.info("RequestVersion: {}, CurrentApiVersion: {}", version, this.apiVersion);
                return this;
            }
        }
        return null;
    }

    /**
     * @param other
     * @param request
     * @return
     */
    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return compareVersion(other.getApiVersion(), this.apiVersion);
    }

    private static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            throw new RuntimeException("compareVersion error:illegal params.");
        }
        if (version1.length() == 0) {
            version1 = "1.0";
        }
        try {
            Double v1 = Double.valueOf(version1);
            Double v2 = Double.valueOf(version2);
            return v1.compareTo(v2);
        } catch (Exception e) {
            throw new RuntimeException("Wrong version number, the version number should be v + number");
        }
    }

}
