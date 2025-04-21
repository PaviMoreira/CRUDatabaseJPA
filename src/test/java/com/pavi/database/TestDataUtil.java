package com.pavi.database;

import com.pavi.database.domain.Author;
import com.pavi.database.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static Author CreateTestAuthor() {
        return Author.builder().
                name("Abigail").
                age(80).
                build();
    }

    public static Author CreateTestAuthorA() {
        return Author.builder().
                name("Peter").
                age(70).
                build();
    }

    public static Author CreateTestAuthorB() {
        return Author.builder().
                name("Joel").
                age(50).
                build();
    }


    public static Book CreateTestBook(final Author author) {
        return Book.builder().
                title("No way out").
                author(author).
                build();
    }

    public static Book CreateTestBook2(final Author author) {
        return Book.builder().
                title("I'm a potato").
                author(author).
                build();
    }
}
