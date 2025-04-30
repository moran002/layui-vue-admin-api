package com.moran.conf.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.crypto.CryptoException;
import com.moran.conf.bean.ResponseBean;
import com.moran.conf.constant.CodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * @author : moran
 * 捕捉全局异常并统一处理
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseBean<Object> defaultErrorHandler(Exception e) {
        e.printStackTrace();
        return ResponseBean.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseBean<Object> handleException(Exception e) {
        e.printStackTrace();
        return ResponseBean.fail(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseBean<Object> handleBindException(BindException e) {
        errorLog(e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseBean.fail(message);
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseBean<Object> sqlException(SQLException e) {
        errorLog(e);
        return ResponseBean.fail("数据异常");
    }
    @ExceptionHandler(value = ServiceException.class)
    public ResponseBean<Object> serviceException(ServiceException e) {
        errorLog(e);
        return ResponseBean.fail(CodeConstant.SERVICE_ERROR, e.getMessage());
    }
    @ExceptionHandler(value = SaTokenException.class)
    public ResponseBean<Object> saTokenException(SaTokenException e) {
        errorLog(e);
        return ResponseBean.fail(CodeConstant.LOGIN_FAIL, "登录失效,请重新登录!!");
    }
    @ExceptionHandler(value = CryptoException.class)
    public ResponseBean<Object> cryptoException(CryptoException e) {
        errorLog(e);
        return ResponseBean.fail(CodeConstant.LOGIN_FAIL, e.getMessage());
    }
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseBean<Object> noHandlerFoundException(NoHandlerFoundException e) {
        errorLog(e);
        return ResponseBean.fail(CodeConstant.SERVICE_ERROR,"请求地址不存在");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseBean<Object> noHandlerFoundException(HttpRequestMethodNotSupportedException e) {
        errorLog(e);
        return ResponseBean.fail(CodeConstant.SERVICE_ERROR,"请求地址不存在");
    }

    private static void errorLog(Exception e) {
        log.error("<!---- 异常信息:{} ----!>", e.getMessage());
    }
}