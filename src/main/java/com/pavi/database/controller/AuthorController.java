package com.pavi.database.controller;

import com.pavi.database.domain.Author;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log
public class AuthorController {

    @GetMapping(path = "/authors")
    public Author retrieveAuthor(){
        return Author.builder().
                name("Abigail").
                age(80).
                build();
    }

    @PostMapping(path = "/authors")
    public Author createAuthor(@RequestBody final Author author){
        log.info("Got Author: " + author.toString());
        return author;
    }

}
