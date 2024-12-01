package com.nhnacademy.accountapi.dto;

import com.nhnacademy.accountapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class StatusDto {
    private Status status; // 예: "active", "inactive", "dormant"
}

