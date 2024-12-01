package com.nhnacademy.accountapi.controller;

import com.nhnacademy.accountapi.dto.LoginRequestDto;
import com.nhnacademy.accountapi.dto.StatusDto;
import com.nhnacademy.accountapi.dto.UserDto;
import com.nhnacademy.accountapi.enums.Role;
import com.nhnacademy.accountapi.enums.Status;
import com.nhnacademy.accountapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //  회원 가입
    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {

        return ResponseEntity.ok(userService.registerUser(userDto));
                //userService.registerUser(userDto);
//                ResponseEntity.status(HttpStatus.CREATED).body(
//                Map.of("message", "User registered successfully", "userId", userDto.getUserId())
//        );
    }

//    // 모든 사용자 목록 조회
//    @GetMapping("/users")
//    public ResponseEntity<?> getAllUsers() {
//        List<UserDto> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    // 로그인
//    @PostMapping("/users/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
//        LoginRequestDto loginRequest = userService.login(loginRequestDto.getUserId(), loginRequestDto.getPassword());
//        return ResponseEntity.ok(loginRequest);
//    }
//
    //  사용자 상태 변경
    @PutMapping("/users/{userId}/status")
    public ResponseEntity<?> updateUserStatus(@PathVariable String userId, @RequestBody Status status) {
        System.out.println("Received HTTP PUT request for userId: " + userId + ", status: " + status);
        userService.updateUserStatus(userId, status);
        return ResponseEntity.ok(Map.of("message", "User status updated successfully"));
    }

//
//    // 사용자 역할 변경 (userId 기반)
//    @PutMapping("/users/{user_id}/role")
//    public ResponseEntity<?> updateUserRole(@PathVariable String user_id, @RequestBody Map<String, String> request) {
//        String role = request.get("role");
//
//        if (role == null || role.isEmpty()) {
//            return ResponseEntity.badRequest().body(Map.of("message", "Role must be provided"));
//        }
//
//        userService.updateUserRole(user_id, Role.valueOf(role.toUpperCase()));
//        return ResponseEntity.ok(Map.of("message", "User role updated successfully"));
//    }

}