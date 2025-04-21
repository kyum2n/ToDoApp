package com.example.webapp.service.impl;

import com.example.webapp.entity.ToDo;
import com.example.webapp.service.ToDoService;
import com.example.webapp.repository.ToDoMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    /** DI */
    private final ToDoMapper toDoMapper;

    @Override
    public List<ToDo> findAllToDo() {
        return toDoMapper.selectAll();
    }

    @Override
    public ToDo findAllByUId(String uId) {
        return toDoMapper.selectByUId(uId);
    }

    @Override
    public ToDo findByIdToDo(Integer id) {
        ToDo toDo = toDoMapper.selectById(id);
        System.out.println("+++++++++++++++++++");
        System.out.println(toDo.getDone());
        return toDoMapper.selectById(id);
    }

    @Override
    public void insertToDo(ToDo toDo) {
        toDoMapper.insertToDo(toDo);
    }

    @Override
    public void updateToDo(ToDo toDo) {
        toDoMapper.updateToDo(toDo);
    }

    @Override
    public void deleteToDo(Integer id) {
        toDoMapper.deleteToDo(id);
    }

}
