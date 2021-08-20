package com.example.springcloud.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class JwtApplicationTests {

    //有效期默认为 2hour
    public static final Long EXPIRATION_TIME = 1000L*60*60*2;
    //签名密钥
    public static final String SECRET_KEY = "q3t6w9z$C&F)J@NcQfTjWnZr4u7xssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";


    @Test
    void contextLoads() {
        SecretKey key= Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        Calendar calendar = Calendar.getInstance();
        String jws = Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setSubject("Joe")
                .setExpiration(new Date(calendar.getTimeInMillis() + EXPIRATION_TIME))
                .signWith(key).compact();
        System.out.println("jws=" + jws);

       Jws<Claims> jwts =  Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws);
        System.out.println(jwts.getBody());

        System.out.println(Jwts.parserBuilder()
               .setSigningKey(key)
               .build()
               .parseClaimsJws(jws));


    }


}
