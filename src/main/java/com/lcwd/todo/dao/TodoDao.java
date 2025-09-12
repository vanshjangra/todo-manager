package com.lcwd.todo.dao;

import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TodoDao {
    private JdbcTemplate template;

    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        String createTable = "";
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public Todo saveTodo(Todo todo){
        String insertQuery = "insert into todos(id, title, content, status, addedDate, toDoDate) values(?, ?, ?, ?, ?, ?)";

        int rows = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getToDoDate());

        logger.info("JDBC OPERATION: {} inserted", rows);

        return todo;
    }
}
