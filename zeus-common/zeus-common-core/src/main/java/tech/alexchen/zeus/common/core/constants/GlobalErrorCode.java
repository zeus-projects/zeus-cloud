package tech.alexchen.zeus.common.core.constants;

import tech.alexchen.zeus.common.core.exception.ErrorCode;

/**
 * 宏观错误码 <br/>
 * 错误码为字符串类型，共 5 位，分成两个部分:错误产生来源+四位数字编号<br/>
 *
 * 错误产生来源分为 C/S/T;<br/>
 * C 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付超时等问题;<br/>
 * S 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题;<br/>
 * T 表示错误来源于第三方服务，比如 CDN 服务出错，消息投递超时等问题;<br/>
 *
 * 四位数字编号从 0001 到 9999，大类之间的步长间距预留 100
 *
 * @author alexchen
 */
public interface GlobalErrorCode {

    ErrorCode SUCCESS = new ErrorCode("00000", "成功");

    ErrorCode CLIENT_ERROR = new ErrorCode("C0001", "用户端错误");

    ErrorCode SERVER_ERROR = new ErrorCode("S0001", "服务端错误");

    ErrorCode TPS_ERROR = new ErrorCode("T0001", "调用第三方服务出错");

}
