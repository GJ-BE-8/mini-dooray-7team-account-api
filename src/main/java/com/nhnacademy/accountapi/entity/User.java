package com.nhnacademy.accountapi.entity;

import com.nhnacademy.accountapi.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {


    @Id
    @Column(name ="user_id")
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Status status = Status.ACTIVE; // 기본 상태 설정

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role = Role.MEMBER; // 기본 역할 설정



}
