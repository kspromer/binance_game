package io.renren.modules.app.interceptor;


import cn.hutool.core.util.ObjectUtil;
import io.jsonwebtoken.Claims;
import io.renren.common.validator.Assert;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.utils.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtils jwtUtils;

    public static final String ACCOUNT_ID = "accountId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Login annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        }else{
            return true;
        }
        if (ObjectUtil.isNotNull(annotation)) {
            //获取用户凭证
            String token = request.getHeader(jwtUtils.getHeader());

            if(StringUtils.isBlank(token)){
                token = request.getParameter(jwtUtils.getHeader());
            }
            Assert.isTrue(StringUtils.isBlank(token),jwtUtils.getHeader() + "Can't be empty");
            Claims claims = jwtUtils.getClaimByToken(token);
            Assert.isTrue(claims == null || jwtUtils.isTokenExpired(claims.getExpiration()),jwtUtils.getHeader() + "Invalid. Please log in again");
            request.setAttribute(ACCOUNT_ID, claims.getSubject());
        }
        return true;
    }
}
