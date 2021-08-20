package com.example.springcloud.filter;

import com.example.springcloud.entity.UserEn;
import com.example.springcloud.service.JwtUserDetailsService;
import com.example.springcloud.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验token
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private JwtUserDetailsService jwtUserDetailsService  ;

    @Resource
    private JwtUtils jwtUtils;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,JwtUserDetailsService jwtUserDetailsService) {
        super(authenticationManager);
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("token");

        ////token不存在，匿名访问，放行
        if (header == null ) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null) {
            WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            // 获取依赖
            jwtUtils = applicationContext.getBean(JwtUtils.class);
            //根据token获取用户
            String username =  jwtUtils.getUserName(token);
            if(username!=null){
                // 查询用户
                UserEn userDetails = (UserEn) jwtUserDetailsService.loadUserByUsername(username);
                //校验用户名
                if((userDetails !=null && username.equals(userDetails.getUsername()) )){
                    return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword() , AuthorityUtils.commaSeparatedStringToAuthorityList(userDetails.getRole()));
                }
            }
            return null;
        }
        return null;
    }


}
