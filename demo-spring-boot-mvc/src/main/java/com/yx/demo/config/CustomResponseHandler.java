package com.yx.demo.config;

import com.yx.demo.utils.JsonData;
import com.yx.demo.utils.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 自定义统一格式的ResponseBody结果返回
 * @author yangxi
 */
@ControllerAdvice
public class CustomResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持advice功能
     * treu=支持，false=不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     *
     * 处理response的具体业务方法
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof JsonData) {
            return o;
        } else if (o instanceof String) {
            return JsonUtils.object2Json(JsonData.buildSuccess(o));
        }
        return JsonData.buildSuccess(o);
    }
}