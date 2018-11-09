package cn.sipin.cloud.member.pojo.util;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

public class ResponseUtil {

    public static ResponseData success() {
        return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(), data);
    }

    public static ResponseData fail() {
        return ResponseData.build(ResponseBackCode.ERROR_FAIL.getValue(), ResponseBackCode.ERROR_FAIL.getMessage());
    }

    public static ResponseData fail(String msg) {
        return ResponseData.build(ResponseBackCode.ERROR_FAIL.getValue(), msg);
    }

    public static ResponseData build(Integer code, String msg) {
        return ResponseData.build(code, msg);
    }
}
