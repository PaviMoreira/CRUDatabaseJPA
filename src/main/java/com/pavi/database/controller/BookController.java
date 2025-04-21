package com.pavi.database.controller;

import com.pavi.database.domain.Book;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {

    @GetMapping(path = "/books")
    public Book retrieveBook(){
        return Book.builder().build();
    }

}
