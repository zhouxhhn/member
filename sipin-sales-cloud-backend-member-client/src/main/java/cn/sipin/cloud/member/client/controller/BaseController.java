package cn.sipin.cloud.member.client.controller;

import org.springframework.validation.BindingResult;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

public class BaseController {

    protected ResponseData getErrorResponse(BindingResult result) {
        //请求的数据参数格式不正确
        if (result.hasErrors()) {
            return ResponseData.build(
                    ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
                    ResponseBackCode.ERROR_PARAM_INVALID.getMessage(), result.getAllErrors()
            );
        }
        return null;
    }
}
