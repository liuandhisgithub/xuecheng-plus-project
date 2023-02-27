package com.xuecheng.base.execption;

import com.xuecheng.enums.CommonError;

/**
 * @program: xuecheng-plus
 * @ClassName XueChengPlusException
 * @description:
 * @author: liujl
 * @create: 2023-02-27 20:11
 * @Version 1.0
 **/
public class XueChengPlusException extends RuntimeException {

    private static final long serialVersionUID = 5565760508056698922L;

    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError) {
        throw new XueChengPlusException(commonError.getErrMessage());
    }

    public static void cast(String errMessage) {
        throw new XueChengPlusException(errMessage);
    }

}
