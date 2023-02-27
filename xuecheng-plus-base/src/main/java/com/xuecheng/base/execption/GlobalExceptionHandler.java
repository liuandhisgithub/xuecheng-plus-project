package com.xuecheng.base.execption;

import com.xuecheng.enums.CommonError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @program: xuecheng-plus
 * @ClassName GlobalExceptionHandler
 * @description:
 * @author: liujl
 * @create: 2023-02-27 20:15
 * @Version 1.0
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(XueChengPlusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(XueChengPlusException e) {
        log.error("【系统异常】{}",e.getErrMessage(),e);
        return new RestErrorResponse(e.getErrMessage());

    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e) {

        log.error("【系统异常】{}",e.getMessage(),e);

        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());

    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse doValidException(MethodArgumentNotValidException argumentNotValidException) {

        BindingResult BindingResult = argumentNotValidException.getBindingResult();

        StringBuffer errMessage = new StringBuffer();

        List<FieldError> fieldErrorList = BindingResult.getFieldErrors();

        fieldErrorList.forEach( err ->
        {
            errMessage.append(err.getField()).append(";");
        });
        log.error(errMessage.toString());
        return new RestErrorResponse(errMessage.toString());

    }

}
