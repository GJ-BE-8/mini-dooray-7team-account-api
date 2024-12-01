package com.nhnacademy.accountapi.entity;
import com.nhnacademy.accountapi.dto.StatusDto;
import com.nhnacademy.accountapi.dto.UserDto;
import com.nhnacademy.accountapi.enums.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// 외부 서비스와의 통신 담당
@Component
@FeignClient(name = "accountApiClient", url = "http://localhost:8080")
public interface UserClient {

    // 회원가입
    @PostMapping("/users")
    UserDto registerUser(@RequestBody UserDto userDto);


    //사용자 목록 조회
    @GetMapping("/users")
    List<UserDto> getUsers(@RequestBody List<User> users);

    // 특정 사용자 조회
    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable("userId") String userId);

    // 로그인
    @PostMapping("/users/login")
    UserDto login(@RequestBody Map<String, String> loginRequest);

    // 사용자 상태 변경
    @PutMapping("/users/{userId}/status")
    UserDto updateStatus(@PathVariable("userId") String userId, @RequestBody StatusDto statusRequest);


    // 사용자 역할 변경
    @PutMapping("/users/{user_id}/role")
    UserDto updateRole(@PathVariable("user_id") String userId, @RequestBody Role roleRequest);
}
