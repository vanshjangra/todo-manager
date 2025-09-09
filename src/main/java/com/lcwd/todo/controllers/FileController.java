package com.lcwd.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/file")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single")
    public String uploadSingle(@RequestParam("image")MultipartFile file){
        logger.info("Name : {}", file.getName());
        logger.info("Content type : {}", file.getContentType());
        logger.info("Original file name : {}", file.getOriginalFilename());
        logger.info("File size : {}", file.getSize());

        return "File test";
    }

    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file -> {
            logger.info("File name {}", file.getOriginalFilename());
            logger.info("File type {}", file.getContentType());
            System.out.println("+++++++++++++++++++++++++++++++++++");
        });
        return "Handling multiple files";
    }
}
