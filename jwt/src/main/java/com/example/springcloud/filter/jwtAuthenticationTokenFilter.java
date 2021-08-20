package com.example.springcloud.filter;

import com.example.springcloud.entity.UserEn;
import com.example.springcloud.service.JwtUserDetailsService;
import com.example.springcloud.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 生成token
 * 验证成功,生成token
 * 验证失败,返回错误
 */
public class jwtAuthenticationTokenFilter  extends UsernamePasswordAuthenticationFilter{


    @Resource
    private JwtUserDetailsService jwtUserDetailsService  ;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public jwtAuthenticationTokenFilter(AuthenticationManager authenticationManager,JwtUserDetailsService jwtUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * 接收并解析用户凭证,验证用户信息
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
         String username =    request.getParameter("username");
         String password = request.getParameter("password");
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
        //交给UsernamePasswordAuthenticationFilter 授权
        return authenticationManager.authenticate(authRequest);
    }

    /**
     * 生成token
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 处理编码方式 防止中文乱码
        response.setContentType("text/json;charset=utf-8");
        UserEn userEn =   (UserEn) authResult.getPrincipal();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        // 获取依赖
        jwtUtils = applicationContext.getBean(JwtUtils.class);
        String token = jwtUtils.generateToken(userEn);
        response.setHeader("token",token);
        // 将反馈塞到HttpServletResponse中返回给前台
        response.getWriter().println(token);

    }

    /**
     * 验证失败
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 处理编码方式 防止中文乱码
        response.setContentType("text/json;charset=utf-8");
        // 将反馈塞到HttpServletResponse中返回给前台
        response.getWriter().write("授权失败"+failed.getMessage());
    }






}
