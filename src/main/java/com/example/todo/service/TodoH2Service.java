package com.example.todo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.repository.*;
import com.example.todo.model.*;
 
@Service
public class TodoH2Service implements TodoRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Todo> getTodos(){
        List<Todo> todo1=db.query("select * from todoList",new TodoRowMapper());
        ArrayList<Todo> todos=new ArrayList<>(todo1);
        return todos;
    }
    @Override
    public Todo getTodobyid(int id){
        try{
        Todo todo=db.queryForObject("select * from todoList where id=?", new TodoRowMapper(),id);
        return todo;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo todoList){
        db.update("insert into todoList(todo,priority,status) values(?,?,?)",todoList.getTodo(),todoList.getPriority(),todoList.getStatus());
        Todo savedtodo=db.queryForObject("select * from todoList where todo=? and priority=? and  status=? ", new TodoRowMapper(), todoList.getTodo(),todoList.getPriority(),todoList.getStatus());
        return savedtodo;
    }

    @Override
    public Todo updateTodo(int id,Todo todoList){
        try{
        if(todoList.getTodo()!=null){
            db.update("update todoList set todo=? where id=?",todoList.getTodo(),id);
        }
        if(todoList.getPriority()!=null){
            db.update("update todoList set priority=? where id=?",todoList.getPriority(),id);
        }
        if(todoList.getStatus()!=null){
            db.update("update todoList set status=? where id=?",todoList.getStatus(),id);
        }
        return getTodobyid(id);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteTodo(int id){
        try{
        db.update("delete from todoList where id=?",id);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.OK);
        }
    }
}