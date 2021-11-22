package com.qijianguo.micro.services.user.infrastructure.config;

import com.qijianguo.micro.services.base.exception.BusinessException;
import com.qijianguo.micro.services.base.exception.CommonError;
import com.qijianguo.micro.services.base.exception.EmBusinessError;
import com.qijianguo.micro.services.base.model.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author qijianguo
 * 异常信息统一处理
 */
@RestControllerAdvice
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    /**
     * 自定义异常
     * @param e 自定义异常信息
     * @return 系统统一的异常信息
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse businessExceptionHandler(BusinessException e) {
        return BaseResponse.fail((CommonError)e.);
    }

    /**
     * 系统内部错误
     * @param e 系统异常
     * @return 系统异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        return BaseResponse.fail(EmBusinessError.UNKNOWN_ERROR, e.getMessage());
    }


    /**
     * 参数异常校验
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BaseResponse parameterExceptionHandler(BindException e) {
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return BaseResponse.fail(EmBusinessError.UNKNOWN_ERROR, fieldError.getDefaultMessage());
            }
        }
        return BaseResponse.fail(EmBusinessError.UNKNOWN_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return BaseResponse.fail(EmBusinessError.UNKNOWN_ERROR, e.getParameterName() + "不能为空!");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public BaseResponse parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        return BaseResponse.fail(EmBusinessError.UNKNOWN_ERROR, "参数不能为空!");
    }


}
