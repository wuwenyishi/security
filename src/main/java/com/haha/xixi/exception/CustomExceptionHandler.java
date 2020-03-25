package com.haha.xixi.exception;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author weilai
 * @date 2019/5/3110:28
 * @description: 说明 项目采用了springboot + oauth2，所以错误产生就分为两部分拦截：
 * <li> 1、springboot的统一错误处理</li>
 * <li>2、oauth2错误处理，这部分参见security-common工程中com.leesky.ezframework.resource.common.exception</li>
 */


@RestControllerAdvice
public class CustomExceptionHandler extends BasicErrorController {
    public CustomExceptionHandler(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {}
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "weilai -13405321158");
    }
    /**
     * 统一异常处理
     *
     * @param e
     * @return
     */
    @SuppressWarnings("rawtypes")
	@ExceptionHandler({Exception.class})
    public ErrorJson processException(Exception e) {
        ErrorJson json = new ErrorJson();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
//        HttpStatus status = getStatus(request);
        if (e instanceof NoHandlerFoundException) {//404错误
            String method = ((NoHandlerFoundException) e).getHttpMethod();
            String path = ((NoHandlerFoundException) e).getRequestURL();
            json.build(HttpStatus.NOT_FOUND.value(), false, method, path, e.getMessage());
        } else if (e instanceof RuntimeException ) {//500错误
            json.build(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, null, null, e.getMessage());
        } else {
            for (Map.Entry m : body.entrySet())
                System.err.println(m.getKey() + "-----" + m.getValue());
        }
//            json.build(status.value(), false, body.get("meth").toString(), null, e.getMessage());
        return json;
    }

}
