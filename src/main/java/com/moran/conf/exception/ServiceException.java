package com.moran.conf.exception;

import java.io.Serial;

/**
 * 服务异常
 *
 * @author moran
 */
public class ServiceException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

}
