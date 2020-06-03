package com.fzu.teamwork.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.PassToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.TokenService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.ErrorStatus;
import com.fzu.teamwork.util.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        User user = null;//登录用户

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //判断是否需要登录权限
        if(method.isAnnotationPresent(LoginToken.class)){
            LoginToken userLoginToken = method.getAnnotation(LoginToken.class);
            if (userLoginToken.required()) {
                //执行认证
                if(token == null){
                    //未登录用户
                    //设置响应状态码
                    response.setStatus(ErrorStatus.NOT_LOGGED_IN);
                    //停止后续访问
                    return false;
                }else{//有token
                    String userId;
                    try {//根据token获取uid
                        userId = JWT.decode(token).getAudience().get(0);
                    } catch (JWTDecodeException j) {
                        //错误的token
                        response.setStatus(ErrorStatus.BAD_TOKEN);
                        return false;
                    }

                    user = userService.getUserById(Integer.parseInt(userId));
                    if(user == null){
                        //用户不存在
                        //设置响应状态码
                        response.setStatus(ErrorStatus.ACCOUNT_NOT_EXIT);
                        //终止后续访问
                        return false;
                    }else{
                        String token2 = tokenService.getToken(user);
                        if(!token2.equals((token))){
                            response.setStatus(ErrorStatus.PASSWORD_ERROR);
                            return false;
                        }
                    }
                }
            }
        }


        //检查是否需要管理员权限
        if(method.isAnnotationPresent(AdminLimit.class)){
            if(!user.getIdentity().equals(UserIdentity.admin)){//没有管理员权限
                //设置响应状态码
                response.setStatus(ErrorStatus.BEYOND_IDENTITY_LIMIT);
                //终止后续访问
                return false;
            }
        }
        if(method.isAnnotationPresent(UserLimit.class)){//需要普通用户权限
            if(!user.getIdentity().equals(UserIdentity.student)
                    && !user.getIdentity().equals(UserIdentity.teacher)){
                //既不是老师也不是学生身份
                //设置响应状态码
                response.setStatus(ErrorStatus.BEYOND_IDENTITY_LIMIT);
                //终止后续访问
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }

}
