package com.example.practice.config;


import com.example.practice.dto.EnvDTO;

import java.util.Map;


public class OS_env {
    public EnvDTO EnvTest() {
        Map<String, String> map = System.getenv();
        EnvDTO envDTO = new EnvDTO();

        envDTO.setUserName(map.get("SPRING_SECURITY_USER_NAME"));
        envDTO.setUserPwd(map.get("SPRING_SECURITY_USER_PASSWORD"));
        envDTO.setUserRole(map.get("SPRING_SECURITY_USER_ROLES"));

        return envDTO;
    }


}
