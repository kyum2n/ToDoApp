package com.example.webapp.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 할일: Form
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoForm {
    /** 할일 ID */
    private Integer id;
    private String uId;
    private LocalDateTime deadline;

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        if (done) {
            return "완료";
        }
        if ((deadline != null) && (deadline.isBefore((LocalDateTime.now())))) {
            return "기간 만료";
        }
        return "미완료";
    }

    /** 할일 */
    @NotBlank(message = "ToDo는 필수입니다.")
    private String todo;

    /** 할일 상세 내용 */
    @Size(min = 1, max = 100, message = "상세 내용은 {min}~{max}자 이내로 입력해주세요.")
    private String detail;

    /** 완료 여부 */
    private Boolean done = false;

    /** 새 항목 여부 */
    private Boolean isNew;

    /** 위치 정보 추가 */
    private Double latitude; // 위도
    private Double longitude; // 경도
}
