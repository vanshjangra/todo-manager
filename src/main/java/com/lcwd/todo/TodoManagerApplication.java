package com.lcwd.todo;

import com.lcwd.todo.dao.TodoDao;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {
    @Autowired
    private TodoDao todoDao;

    Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Application started:");
//        JdbcTemplate template = todoDao.getTemplate();
//        logger.info("Template object : {}", template);
//        logger.info("Template object : {}", template.getDataSource());

        Todo todo = new Todo();
        todo.setId(123);
        todo.setTitle("This is testing spring jdbc");
        todo.setContent("Wow its working");
        todo.setStatus("PENDING");
        todo.setAddedDate(new Date());
        todo.setToDoDate(new Date());

        todoDao.saveTodo(todo);
    }
}
