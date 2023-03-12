package com.example.todo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.model.*;
import com.example.todo.service.*;

@RestController
public class TodoController{
    @Autowired
    private TodoH2Service todoService;
    @GetMapping("/todos")
    public ArrayList<Todo> getTodos(){
        return todoService.getTodos();
    }
    @GetMapping("/todos/{id}")
    public Todo getTodobyid(@PathVariable("id") int id) {
        return todoService.getTodobyid(id);
    }
    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todoList) {
        return todoService.addTodo(todoList);
    }
    
    
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todoList) {
        return todoService.updateTodo(id, todoList);
    }
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable("id") int id){
        todoService.deleteTodo(id);
    }
}
