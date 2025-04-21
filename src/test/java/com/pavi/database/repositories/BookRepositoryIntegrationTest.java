package com.pavi.database.repositories;


import com.pavi.database.TestDataUtil;
import com.pavi.database.domain.Author;
import com.pavi.database.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookRepositoryIntegrationTest {

    private final BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest){
        this.underTest = underTest;
    }



    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthor();
        Book book = TestDataUtil.CreateTestBook(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getId());
        assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(book);
    }


    @Test
    @Transactional
    public void testThatManyAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthor();

        Book book1 = TestDataUtil.CreateTestBook(author);
        Book book2 = TestDataUtil.CreateTestBook2(author);


        underTest.save(book1);
        underTest.save(book2);

        Iterable<Book> result = underTest.findAll();

        Assertions.assertThat(result)
                .isNotNull()
                .contains(book1, book2);
    }



    @Test
    public void testThatUpdatesBook(){
        Author author = TestDataUtil.CreateTestAuthor();

        Book book = TestDataUtil.CreateTestBook(author);
        underTest.save(book);
        book.setTitle("UPDATED");

        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getId());

        Assertions.assertThat(result).isPresent().isNotNull();
        Assertions.assertThat(result.get().getTitle()).isEqualTo(book.getTitle());
    }



    @Test
    public void testThatDeletesBook(){
        Author author = TestDataUtil.CreateTestAuthor();

        Book book = TestDataUtil.CreateTestBook(author);
        underTest.save(book);

        underTest.deleteById(book.getId());

        Optional<Book> result = underTest.findById(book.getId());

        Assertions.assertThat(result).isEmpty();
    }




}
