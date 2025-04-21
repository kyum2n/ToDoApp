package com.example.webapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.webapp.entity.ToDo;

@Mapper
public interface ToDoMapper {
    List<ToDo> selectAll();

    ToDo selectByUId(@Param("uId") String uId);

    ToDo selectById(@Param("id") Integer id);

    void insertToDo(ToDo toDo);

    void updateToDo(ToDo toDo);

    void deleteToDo(Integer id);

}
