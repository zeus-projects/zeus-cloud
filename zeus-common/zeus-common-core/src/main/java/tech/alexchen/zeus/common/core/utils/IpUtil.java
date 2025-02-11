package tech.alexchen.zeus.common.core.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author alexchen
 * @since 2025-02-11 11:49
 */
public class IpUtil {

    public static String getClientIp(HttpServletRequest request) {
        String ipAddress = null;

        // 检查X-Forwarded-For头
        ipAddress = request.getHeader("X-Forwarded-For");
        if (isInvalidIp(ipAddress)) {
            // 检查X-Real-IP头
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (isInvalidIp(ipAddress)) {
            // 检查其他可能的代理头
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (isInvalidIp(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isInvalidIp(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isInvalidIp(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        // 若以上头信息均无效，使用getRemoteAddr()
        if (isInvalidIp(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        // 处理多IP情况（取第一个有效IP）
        if (ipAddress != null && ipAddress.contains(",")) {
            String[] ips = ipAddress.split(",");
            for (String ip : ips) {
                if (!isInvalidIp(ip)) {
                    ipAddress = ip.trim();
                    break;
                }
            }
        }

        // 转换本地IPv6地址
        if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
            ipAddress = "127.0.0.1";
        }
        return ipAddress != null ? ipAddress : "";
    }

    private static boolean isInvalidIp(String ip) {
        return ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip);
    }
}
