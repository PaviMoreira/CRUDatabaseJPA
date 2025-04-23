package com.pavi.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavi.database.domain.Author;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JacksonTests {

    @Test
    public void testThatOjbectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        Author author = Author.builder().
                name("Jackson").
                age(28).
                build();

        String result = objectMapper.writeValueAsString(author);

        assertThat(result).isEqualTo("{" +
                "\"id\":null, " +
                "\"name\":\"Jackson\", " +
                "\"AuthorAge\":28}");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJson() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        Author author = Author.builder().
                name("Jackson").
                age(28).
                build();

        String json = "{" +
                "\"foo\":\"bar\", " +
                "\"id\":null, " +
                "\"name\":\"Jackson\", " +
                "\"AuthorAge\":28}";

        Author result = objectMapper.readValue(json, Author.class);

        assertThat(result).isEqualTo(author);
    }



}
