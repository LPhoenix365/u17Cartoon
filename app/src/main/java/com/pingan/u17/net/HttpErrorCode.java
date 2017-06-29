package com.pingan.u17.net;

/**
 * 请求后的一些特殊错误码
 * Created by pa_zhiqiang on 2016-09-20.
 */
public class HttpErrorCode {
    public static final String REQUEST_OK     = "0";
    public static final String SYSTEM_ERROR   = "-1";//后台系统异常
    public static final String REQUEST_FAILED = "-2";

    public static final String PATIENT_GROUP_RENAME_CODE = "12";//创建患者分组重名
    public static final String OFF_LINE_ERROR_CODE       = "E27";//被顶下线
    public static final String TICKET_OUT_OF_DATE        = "E26";//ticket已失效 or 登录超时请重新登录
    public static final String IP_NOT_EXIST              = "E29";//IP未存在于白名单
    public static final String REQUEST_PARAM_NOT_LEGAL   = "E001";//参数不合法

    public static final String SIGN_IN_NOT_IN_RANGE = "2";//签到不在范围内的错误码（500米）

    public static final String ZN_RESULT_SUCESS  = "0";
    public static final String ZN_REGISTE_SUCESS = "200";
    public static final String ZN_SID_ERROR      = "-4";     //知鸟账户sid失效，（被顶下线或者过期）
}
