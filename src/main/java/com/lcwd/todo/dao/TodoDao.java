package com.lcwd.todo.dao;

import com.lcwd.todo.helper.Helper;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TodoDao {
    private JdbcTemplate template;

    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        String createTable = "create table IF NOT EXISTS todos(id int primary key, title varchar(100) not null, content varchar(500), status varchar(10) not null, addedDate datetime, toDoDate datetime)";
        int update = template.update(createTable);
        logger.info("TODO TABLE CREATED {} ", update);
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

    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos WHERE id = ?";
        Map<String, Object> todoData = template.queryForMap(query, id);

        Todo todo = new Todo();

        todo.setId((int) todoData.get("id"));
        todo.setTitle((String) todoData.get("title"));
        todo.setContent((String) todoData.get("content"));
        todo.setStatus((String) todoData.get("status"));
        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
        todo.setToDoDate(Helper.parseDate((LocalDateTime) todoData.get("toDoDate")));

        return todo;
    }

    public List<Todo> getAllTodos(){
        String query = "select * from todos";
        List<Map<String, Object>> maps = template.queryForList(query);

        List<Todo> todos = maps.stream().map((map) -> {
            Todo todo = new Todo();

            todo.setId((int) map.get("id"));
            todo.setTitle((String) map.get("title"));
            todo.setContent((String) map.get("content"));
            todo.setStatus((String) map.get("status"));
            try {
                todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
                todo.setToDoDate(Helper.parseDate((LocalDateTime) map.get("toDoDate")));
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }

            return todo;
        }).collect(Collectors.toList());

        return todos;
    }
}
