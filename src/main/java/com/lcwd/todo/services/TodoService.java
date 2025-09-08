package com.lcwd.todo.services;

import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoService {
    List<Todo> todos = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(TodoService.class);

    public Todo createTodo(Todo todo){
        todos.add(todo);
        logger.info("Todos {}", this.todos);
        return todo;
    }
}
