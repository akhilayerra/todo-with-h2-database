package com.example.todo.repository;
import java.util.*;
import com.example.todo.model.*;
public interface TodoRepository{
    ArrayList<Todo> getTodos();
    Todo getTodobyid(int id);
    Todo addTodo(Todo todoList);
    Todo updateTodo(int id,Todo todoList);
    void deleteTodo(int id);
}