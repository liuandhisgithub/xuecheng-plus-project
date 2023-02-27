package com.xuecheng.base.execption;

/**
 * @program: xuecheng-plus
 * @ClassName RestErrorResponse
 * @description: 错误信息封装返回
 * @author: liujl
 * @create: 2023-02-27 20:13
 * @Version 1.0
 **/
public class RestErrorResponse {

    private String errMessage;

    public RestErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
