package com.example.webapp.service;

import java.util.List;

import com.example.webapp.entity.ToDo;

/** * ToDo: 서비스 */
public interface ToDoService {

    /** 모든 '할일'을 조회 */
    List<ToDo> findAllToDo(); // -> SELECT * FROM todos

    /** 특정 사용자의 '할일'을 조회 */
    ToDo findAllByUId(String uId);

    /** 특정 ID의 '할일'을 조회 */
    ToDo findByIdToDo(Integer id); // -> SELECT * FROM todos WHERE id = ?

    /** '할일'을 새로 등록 */
    void insertToDo(ToDo toDo); // -> INSERT INTO todos (...)

    /** '할일'을 업데이트 */
    void updateToDo(ToDo toDo); // -> UPDATE todos SET ...

    /** 특정 ID의 '할일'을 삭제 */
    void deleteToDo(Integer id); // -> DELETE FROM todos WHERE id = ?
}
