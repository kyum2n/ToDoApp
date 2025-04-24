package com.example.webapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 생성자
public class User {
    private String uName;
    private String uId;
    private String uPwd;
    private String uMail;
}
