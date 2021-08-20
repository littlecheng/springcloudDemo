package com.example.springcloud.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Configuration
public class JwtUtils {

    @Value("${jwt.secret}")
    private   String secret;

    @Value("${jwt.expire}")
    private   Long expire;

    public JwtUtils( ){
        System.out.println("JwtUtils =1=="+this.expire);
    }


    public JwtUtils( String secret, Long expire){
        this.secret = secret;
        this.expire = expire;
        System.out.println("JwtUtils =2=="+this.expire);
    }

    /**
     * 生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        Calendar calendar = Calendar.getInstance();
        String token = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(calendar.getTimeInMillis() + expire))
                .signWith(key).compact();
        System.out.println(token);
        return token;
    }

    /**
     * 从令牌中获取数据声明
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        try {
            return   Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }catch (Exception e){
            throw new RuntimeException("令牌失效");
        }
    }

    /**
     * 获取用户名
     * @param token 令牌
     * @return 用户名
     */
    public String getUserName(String token) {
        try{
            return getClaimsFromToken(token).getSubject();
        }catch (Exception e){
            return null;
        }
    }


    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        if( claims!=null){
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        }
        return true;
    }

}
