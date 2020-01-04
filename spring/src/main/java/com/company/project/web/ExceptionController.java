package com.company.project.web;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Context;
import com.company.project.core.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
	
	private Context context = new Context();

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public JSONObject handle401(ShiroException e) {
        return context.handleRequest(401);
    };

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public JSONObject handle401() {
    	return context.handleRequest(401);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONObject globalException(HttpServletRequest request, Throwable ex) {
    	return context.handleRequest(400);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}

