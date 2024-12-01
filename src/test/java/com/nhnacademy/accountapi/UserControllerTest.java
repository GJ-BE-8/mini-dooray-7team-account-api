//package com.nhnacademy.accountapi;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nhnacademy.accountapi.controller.UserController;
//import com.nhnacademy.accountapi.dto.LoginRequestDto;
//import com.nhnacademy.accountapi.dto.UserDto;
//import com.nhnacademy.accountapi.service.UserService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Map;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    @DisplayName("회원 가입 테스트")
//    void testRegisterUser() throws Exception {
//        UserDto userDto = new UserDto("user1", "user1@example.com", "password123", "MEMBER", "ACTIVE");
//
//        mockMvc.perform(post("/api/users/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userDto)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.message").value("User registered successfully"))
//                .andExpect(jsonPath("$.userId").value(userDto.getUserId()));
//    }
//
//    @Test
//    @DisplayName("유저 조회 테스트")
//    void testGetUserById() throws Exception {
//        UserDto userDto = new UserDto("user1", "user1@example.com", "password123", "MEMBER", "ACTIVE");
//
//        Mockito.when(userService.getUserById("user1")).thenReturn(userDto);
//
//        mockMvc.perform(get("/api/users/user1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.user_id").value("user1")) // 경로 수정
//                .andExpect(jsonPath("$.email").value("user1@example.com"));
//    }
//
//
//    @Test
//    @DisplayName("로그인 테스트")
//    void testLogin() throws Exception {
//        LoginRequestDto loginRequestDto = new LoginRequestDto("user1", "password123");
//        UserDto userDto = new UserDto("user1", "user1@example.com", null, "MEMBER", "ACTIVE");
//
//        Mockito.when(userService.authenticate("user1", "password123")).thenReturn(userDto);
//
//        mockMvc.perform(post("/api/users/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequestDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("Login successful"))
//                .andExpect(jsonPath("$.user.user_id").value("user1")); // 수정된 경로
//    }
//
//
//    @Test
//    @DisplayName("유저 상태 변경 테스트")
//    void testUpdateUserStatus() throws Exception {
//        mockMvc.perform(put("/api/users/user1/status")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(Map.of("status", "INACTIVE"))))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("User status updated successfully"));
//    }
//}