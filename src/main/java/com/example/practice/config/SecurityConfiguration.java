package com.example.practice.config;

import com.example.practice.dto.EnvDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // After deploying as a jar, the file inside the jar cannot be called as a file.
        // Copy the file and get the path of the main copy
        InputStream inputStream = new ClassPathResource("properties/securityUserInfo.yml").getInputStream();
        File file = File.createTempFile("securityUserInfo", "yml");
        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        String filePath = file.getAbsolutePath();

        Reader yamlFile = new FileReader(filePath);
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


/*        auth
                .inMemoryAuthentication()
                .withUser("test").password(passwordEncoder().encode("1234"))
                .roles("USER");*/

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
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
