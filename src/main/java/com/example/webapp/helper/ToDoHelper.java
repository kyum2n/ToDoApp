package com.example.webapp.helper;

import com.example.webapp.entity.ToDo;
import com.example.webapp.form.ToDoForm;

/**
 * ToDo: 헬퍼
 */
public class ToDoHelper {
    /**
     * ToDo로 변환
     */
    public static ToDo convertToDo(ToDoForm form) {
        ToDo todo = new ToDo();

        // ID는 수정할 때만 사용하므로 null 체크
        if (form.getId() != null) {
            todo.setId(form.getId());
        }

        // uId 세션에서 주입됐는지 확인하고 넣기
        // uId는 users 테이블의 기본키이자, todos.uId의 외래키임
        // 이 값이 todo 내용이나 detail, 혹은 빈 값으로 들어가면 PostgreSQL이 무조건 참조키 오류로 막음
        // 그래서 form.getUId() 값이 반드시 로그인 세션에서 주입된 유효한 값이어야 함.
        // 위 내용을 검증하고 넣기 위해 isBlank() 체크도 같이 부여함.
        if ((form.getUId() != null) && !(form.getUId().isBlank())) {
            todo.setUId(form.getUId());
        } else {
            throw new IllegalArgumentException("유효하지 않은 사용자 ID입니다.");
        }

        todo.setTodo(form.getTodo() != null ? form.getTodo() : "");
        todo.setDetail(form.getDetail() != null ? form.getDetail() : "");
        todo.setDone(Boolean.TRUE.equals(form.getDone()));
        todo.setDeadline(form.getDeadline());

        return todo;
    }

    /**
     * ToDoForm으로 변환
     */
    public static ToDoForm convertToDoForm(ToDo todo) {
        ToDoForm form = new ToDoForm();
        form.setId(todo.getId());
        form.setUId(todo.getUId());
        form.setTodo(todo.getTodo());
        form.setDetail(todo.getDetail());
        form.setDone(form.getDone() != null ? form.getDone() : false);
        form.setDeadline(todo.getDeadline());

        // 업데이트 화면 설정
        form.setIsNew(false);

        return form;
    }
}
