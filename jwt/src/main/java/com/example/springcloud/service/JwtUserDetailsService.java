package com.example.springcloud.service;

import com.example.springcloud.entity.UserEn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 加载用户信息
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("查询数据库操作");
        UserEn userEn =  userService.selectByUserName(username);
        if(userEn ==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new UserEn(userEn.getId(),username,userEn.getPassword(),userEn.getToken(),userEn.getRole(), AuthorityUtils.commaSeparatedStringToAuthorityList(userEn.getRole()));
    }

}
