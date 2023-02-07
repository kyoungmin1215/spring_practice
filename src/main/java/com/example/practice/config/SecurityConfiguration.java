package com.example.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("code1").password(passwordEncoder().encode("code123")).roles("ADMIN")
//                .and()
//                .withUser("code2").password(passwordEncoder().encode("code456")).roles("USER");
//
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth
                .inMemoryAuthentication()
                .withUser("code1").password(passwordEncoder().encode("code123")).roles("ADMIN")
                .and()
                .withUser("code2").password(passwordEncoder().encode("code456")).roles("USER");*/


        Reader yamlFile = new FileReader("C:\\Users\\wkm25\\Desktop\\practice\\me\\springboot\\practice\\src\\main\\resources\\properties\\securityUserInfo.yml");
        Map<String, ArrayList> yamlMaps = new Yaml().load(yamlFile);

        //LinkedHashMap eachUser = (LinkedHashMap)(yamlMaps.get("user").get(0));


        for(int i=0; i<yamlMaps.get("user").size(); i++) {
            LinkedHashMap eachUser = (LinkedHashMap)(yamlMaps.get("user").get(i));
            auth
                    .inMemoryAuthentication()
                    .withUser(String.valueOf(eachUser.get("name")))
                    .password(passwordEncoder().encode(String.valueOf(eachUser.get("password"))))
                    .roles(String.valueOf(eachUser.get("roles")));
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // post형식을 받기위한 코드 (spring security는 기본적으로 csrf에 대해 체크한다)
        http.csrf().disable();

        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
