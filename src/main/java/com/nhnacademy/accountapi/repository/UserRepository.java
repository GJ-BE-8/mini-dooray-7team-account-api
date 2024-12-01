package com.nhnacademy.accountapi.repository;

import com.nhnacademy.accountapi.dto.StatusDto;
import com.nhnacademy.accountapi.dto.UserDto;
import com.nhnacademy.accountapi.entity.User;
import com.nhnacademy.accountapi.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // 기존 메서드
//    User registerUser(User user);
//    Optional<User> findById(String userId);
//    Optional<User> findByEmail(String email);
//    LoginRequestDto findUserByIdAndPassword(String id, String password);
//    StatusDto updateStatus(String id, StatusDto status);
//    List<User> findAll();
//

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.userId = :userId")
    int updateStatus(@Param("userId") String userId, @Param("status") Status status);


//    Optional<User> findUserByRole(Role role);
}

