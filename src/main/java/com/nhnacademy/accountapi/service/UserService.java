package com.nhnacademy.accountapi.service;

import com.nhnacademy.accountapi.dto.LoginRequestDto;
import com.nhnacademy.accountapi.dto.StatusDto;
import com.nhnacademy.accountapi.entity.User;
import com.nhnacademy.accountapi.entity.UserClient;
import com.nhnacademy.accountapi.dto.UserDto;
import com.nhnacademy.accountapi.enums.Role;
import com.nhnacademy.accountapi.enums.Status;
import com.nhnacademy.accountapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.mapping.UserDefinedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserClient userClient;


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserClient userClient, UserRepository userRepository) {
        this.userClient = userClient;
        this.userRepository = userRepository;
    }

    // 회원가입 (DB 저장 + 외부 요청)
    public UserDto registerUser(UserDto userDto) {

        // 1. DB에 저장
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        UserDto userdto1 = new UserDto(user.getUserId(), user.getPassword(), user.getEmail(), user.getRole(), user.getStatus());
        // 2. 외부 API 요청
        userClient.registerUser(userdto1);
        return userdto1;

    }

//        // 사용자 조회 (DB + 외부 요청)
//    public UserDto getUserById(String userId) {
//        // 1. DB에서 조회
//        userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId));
//
//        // 2. 외부 API 요청
//        return userClient.getUserById(userId);
//    }
//
//    // 로그인
//    public LoginRequestDto login(String userId, String password) {
//        return userRepository.findUserByIdAndPassword(userId, password);
//        // 외부 API 호출
//
//    }
//
//    //사용자 목록 조회
//    public List<UserDto> getAllUsers() {
//        List<User> users = userRepository.findAll();
//        return userClient.getUsers(users);
//
//    }
//    // 사용자 상태 변경 (DB + 외부 API)
//    @Transactional
//    public UserDto updateUserStatus(String userId, StatusDto status) {
//        // DB에서 상태 업데이트
//        StatusDto updated = userRepository.updateStatus(userId, status);
//        // 외부 API
//        userRepository.updateStatus(userId, status);
//        return userClient.updateStatus(userId, status);
//    }
    @Transactional
    public UserDto updateUserStatus(String userId, Status status) {
        System.out.println("Updating user in database: userId=" + userId + ", status=" + status);
        int rowsUpdated = userRepository.updateStatus(userId, status);
        System.out.println("Rows updated in database: " + rowsUpdated); // 디버깅 로그

        if (rowsUpdated == 0) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: " + userId);
        }

        return userClient.updateStatus(userId, new StatusDto(status));
    }

//
//
//    }
//
//    // 사용자 역할 변경
//    public UserDto updateUserRole(String user_id, Role role) {
//        // 1. DB에서 사용자 조회
//        User user = userRepository.findById(user_id)
//                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + user_id));
//
//        // 2. 역할 변경
//        user.setRole(role);
//        //업데이트
//        userRepository.save(user);
//
//        // 3. 외부 API 호출
//        return userClient.updateRole(user_id, role);
//    }

}