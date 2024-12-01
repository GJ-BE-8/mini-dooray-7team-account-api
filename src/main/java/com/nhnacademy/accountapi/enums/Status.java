package com.nhnacademy.accountapi.enums;

public enum Status {
    ACTIVE("가입"),     // 활성화 상태
    DORMANT("휴면"),    // 휴면 상태
    ENDED("탈퇴");      // 탈퇴 상태

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

