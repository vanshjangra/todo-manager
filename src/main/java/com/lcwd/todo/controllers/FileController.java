package com.lcwd.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
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

    @GetMapping("/serve-image")
    public void serveImageHandler(HttpServletResponse response){
        try {
            InputStream fileInputStream = new FileInputStream("images/download.png");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream, response.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
