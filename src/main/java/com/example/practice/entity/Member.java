package com.example.practice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
public class Member {

    private Long id;
    private String userId;
    private String userName;
    private String userPwd;
}
