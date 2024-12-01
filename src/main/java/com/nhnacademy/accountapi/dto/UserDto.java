package com.nhnacademy.accountapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.accountapi.enums.Role;
import com.nhnacademy.accountapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String email;
    private String password;
    private Role role;
    private Status status;
}
