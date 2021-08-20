package com.example.springcloud.config;

import com.example.springcloud.filter.jwtAuthenticationTokenFilter;
import com.example.springcloud.service.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.springcloud.filter.JWTAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity// 这个注解必须加，开启Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(jwtUserDetailsService);
        auth.userDetailsService(jwtUserDetailsService);
    }

    /**
     * 定义访问权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure....");
        http.authorizeRequests()
                .antMatchers("/", "/home","/favicon.ico","/user/signUp","/signUp").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                //.failureForwardUrl("/error")
                .permitAll().and()
                .logout().permitAll().and()
                .csrf().disable();


        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(new jwtAuthenticationTokenFilter(getAuthenticationManager(),jwtUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new JWTAuthenticationFilter(getAuthenticationManager(),jwtUserDetailsService));

        //退出登录器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        //添加自定义未授权和未登录结果返回
      /*  http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);*/
    }

    /**
     * 选择不加密
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        System.out.println("getPasswordEncoder()");
        return  NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        System.out.println("getAuthenticationManager()");
        return super.authenticationManagerBean();
    }


}
