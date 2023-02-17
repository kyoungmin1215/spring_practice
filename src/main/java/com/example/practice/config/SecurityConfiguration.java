package com.example.practice.config;

import com.example.practice.dto.EnvDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        //Reader yamlFile = new FileReader("C:\\Users\\wkm25\\Desktop\\practice\\me\\springboot\\practice\\src\\main\\resources\\properties\\securityUserInfo.yml");
        Reader yamlFile = new FileReader("src/main/resources/properties/securityUserInfo.yml");
        Map<String, ArrayList> yamlMaps = new Yaml().load(yamlFile);

        for(int i=0; i<yamlMaps.get("user").size(); i++) {
            LinkedHashMap eachUser = (LinkedHashMap)(yamlMaps.get("user").get(i));
            auth
                    .inMemoryAuthentication()
                    .withUser(String.valueOf(eachUser.get("name")))
                    .password(passwordEncoder().encode(String.valueOf(eachUser.get("password"))))
                    .roles(String.valueOf(eachUser.get("roles")));
        }

        // 시스템변수에서 가져온 값으로 시큐리티에 설정하기
/*        EnvDTO env = new OS_env().EnvTest();

        auth
                .inMemoryAuthentication()
                .withUser(env.getUserName()).password(passwordEncoder().encode(env.getUserPwd()))
                .roles(env.getUserRole());*/

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // post형식을 받기위한 코드 (spring security는 기본적으로 csrf에 대해 체크한다)
        http.csrf().disable();

        // 동일 도메인에서 접근 허용
        http.headers().frameOptions().sameOrigin();

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        // 시큐리티 홀더의 공유 전략 설정 - 쓰레드가 생성하는 하위 쓰레드까지 자원공유
        //SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
