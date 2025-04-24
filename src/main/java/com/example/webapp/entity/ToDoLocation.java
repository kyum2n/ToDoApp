package com.example.webapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoLocation {
    private Integer id;
    private String todo;
    private Double latitude;
    private Double longitude;
    private String uId;
}
/**
 * 지도 마커 표시를 위한 위치 정보 객체입니다다.
 * DTO 역할을 하지만 폴더 통일을 위해 Helper에 둡니다.
 * 만일 DTO폴더가 생긴다면 거기로 옮겨야합니다.
 */