package com.example.webapp.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    private int id;
    private String uId;
    private String todo;
    private String detail;
    private Boolean done;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 지도 위치 표시용 위도&경도
    private Double latitude; // 위도
    private Double longitude; // 경도

}
