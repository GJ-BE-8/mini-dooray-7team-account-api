//package com.nhnacademy.accountapi;
//
//import com.nhnacademy.accountapi.dto.UserDto;
//import com.nhnacademy.accountapi.entity.User;
//import com.nhnacademy.accountapi.enums.Role;
//import com.nhnacademy.accountapi.enums.Status;
//import com.nhnacademy.accountapi.exception.UserNotFoundException;
//import com.nhnacademy.accountapi.repository.UserRepository;
//import com.nhnacademy.accountapi.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.*;
//
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("회원 가입 성공")
//    void testRegisterUser() {
//        UserDto userDto = new UserDto("user1", "user1@example.com", "password123", "MEMBER", "ACTIVE");
//
//        when(userRepository.findByEmail("user1@example.com")).thenReturn(Optional.empty());
//
//        userService.registerUser(userDto);
//
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    @DisplayName("회원 가입 실패 - 이메일 중복")
//    void testRegisterUser_EmailConflict() {
//        UserDto userDto = new UserDto("user1", "duplicate@example.com", "password123", "MEMBER", "ACTIVE");
//
//        when(userRepository.findByEmail("duplicate@example.com")).thenReturn(Optional.of(new User()));
//
//        assertThatThrownBy(() -> userService.registerUser(userDto))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Email is already in use: duplicate@example.com");
//    }
//
//    @Test
//    @DisplayName("유저 상태 변경 - 성공")
//    void testUpdateUserStatus() {
//        User user = new User();
//        user.setId("user1");
//        user.setStatus(Status.ACTIVE);
//
//        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
//
//        userService.updateUserStatus("user1", "DORMANT");
//
//        assertThat(user.getStatus()).isEqualTo(Status.DORMANT);
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    @DisplayName("유저 상태 변경 - 실패 (존재하지 않는 유저)")
//    void testUpdateUserStatus_UserNotFound() {
//        when(userRepository.findById("user1")).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> userService.updateUserStatus("user1", "DORMANT"))
//                .isInstanceOf(UserNotFoundException.class)
//                .hasMessageContaining("User not found: user1");
//    }
//
//    @Test
//    @DisplayName("유저 조회 - 성공")
//    void testGetUserById() {
//        User user = new User();
//        user.setId("user1");
//        user.setEmail("user1@example.com");
//        user.setPassword("password123");
//        user.setRole(Role.MEMBER);
//        user.setStatus(Status.ACTIVE);
//
//        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
//
//        UserDto userDto = userService.getUserById("user1");
//
//        assertThat(userDto.getUserId()).isEqualTo("user1");
//        assertThat(userDto.getEmail()).isEqualTo("user1@example.com");
//    }
//
//    @Test
//    @DisplayName("유저 조회 - 실패 (존재하지 않는 유저)")
//    void testGetUserById_UserNotFound() {
//        when(userRepository.findById("user1")).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> userService.getUserById("user1"))
//                .isInstanceOf(UserNotFoundException.class)
//                .hasMessageContaining("User not found: user1");
//    }
//
//    @Test
//    @DisplayName("유저 수정 - 성공")
//    void testUpdateUser() {
//        User user = new User();
//        user.setId("user1");
//        user.setEmail("user1@example.com");
//        user.setPassword("oldpassword");
//
//        UserDto updatedUserDto = new UserDto("user1", "newemail@example.com", "newpassword", "MEMBER", "ACTIVE");
//
//        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
//
//        userService.updateUser("user1", updatedUserDto);
//
//        assertThat(user.getEmail()).isEqualTo("newemail@example.com");
//        assertThat(user.getPassword()).isEqualTo("newpassword");
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    @DisplayName("유저 수정 - 실패 (존재하지 않는 유저)")
//    void testUpdateUser_UserNotFound() {
//        UserDto updatedUserDto = new UserDto("user1", "newemail@example.com", "newpassword", "MEMBER", "ACTIVE");
//
//        when(userRepository.findById("user1")).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> userService.updateUser("user1", updatedUserDto))
//                .isInstanceOf(UserNotFoundException.class)
//                .hasMessageContaining("User not found: user1");
//    }
//
//    @Test
//    @DisplayName("유저 역할 변경 - 성공")
//    void testUpdateUserRole() {
//        User user = new User();
//        user.setId("user1");
//        user.setRole(Role.MEMBER);
//
//        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
//
//        userService.updateUserRole("user1", "PROJECT_ADMIN");
//
//        assertThat(user.getRole()).isEqualTo(Role.PROJECT_ADMIN);
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    @DisplayName("유저 인증 - 성공")
//    void testAuthenticate_Success() {
//        User user = new User();
//        user.setId("user1");
//        user.setPassword("password123");
//        user.setEmail("user1@example.com");
//        user.setRole(Role.MEMBER);
//        user.setStatus(Status.ACTIVE);
//
//        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
//
//        UserDto userDto = userService.authenticate("user1", "password123");
//
//        assertThat(userDto.getUserId()).isEqualTo("user1");
//        assertThat(userDto.getEmail()).isEqualTo("user1@example.com");
//    }
//
//    @Test
//    @DisplayName("유저 인증 - 실패 (비밀번호 불일치)")
//    void testAuthenticate_InvalidPassword() {
//        User user = new User();
//        user.setId("user1");
//        user.setPassword("password123");
//
//        when(userRepository.findById("user1")).thenReturn(Optional.of(user));
//
//        assertThatThrownBy(() -> userService.authenticate("user1", "wrongpassword"))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Invalid user ID or password");
//    }
//
//    @Test
//    @DisplayName("유저 인증 - 실패 (존재하지 않는 유저)")
//    void testAuthenticate_UserNotFound() {
//        when(userRepository.findById("user1")).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> userService.authenticate("user1", "password123"))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Invalid user ID or password");
//    }
//}