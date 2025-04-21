package com.pavi.database.repositories;


import com.pavi.database.TestDataUtil;
import com.pavi.database.domain.Author;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorRepositoryIntegrationTest {

    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest){
        this.underTest = underTest;
    }


    @Test
    @Transactional
    @Rollback
    public void testThatAuthorCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.CreateTestAuthor();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }


    @Test
    @Transactional
    @Rollback
    public void testThatManyAuthorCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.CreateTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.CreateTestAuthorB();
        underTest.save(authorB);

        Iterable<Author> result = underTest.findAll();

        assertThat(result)
                .isNotNull()
                .contains(authorA, authorB);
    }


    @Test
    public void testThatUpdatesAuthor(){
        Author authorA = TestDataUtil.CreateTestAuthorA();
        underTest.save(authorA);
        authorA.setName("UPDATED");

        underTest.save(authorA);

        Optional<Author> result = underTest.findById(authorA.getId());

        assertThat(result).isPresent().isNotNull();
        assertThat(result.get().getName()).isEqualTo(authorA.getName());
    }



    @Test
    public void testThatDeletesAuthor(){
        Author author = TestDataUtil.CreateTestAuthor();
        underTest.save(author);

        underTest.deleteById(author.getId());

        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorWithAgeLessThan(){
        Author author = TestDataUtil.CreateTestAuthor();
        underTest.save(author);
        Author authorA = TestDataUtil.CreateTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.CreateTestAuthorB();
        underTest.save(authorB);

        Iterable<Author> result = underTest.ageLessThan(71);

        assertThat(result)
                .doesNotContain(author);

    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan(){
        Author author = TestDataUtil.CreateTestAuthor();
        underTest.save(author);
        Author authorA = TestDataUtil.CreateTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.CreateTestAuthorB();
        underTest.save(authorB);

        Iterable<Author> result = underTest.findAuthorWithAgeGreaterThan(71);

        assertThat(result).doesNotContain(authorA,authorB);

    }

}
