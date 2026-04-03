package com.example.booksapi.controller;

import com.example.booksapi.model.Book;
import com.example.booksapi.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerSearchTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();

        Book book1 = new Book();
        book1.setTitle("The Great Gatsby");
        book1.setAuthor("F. Scott Fitzgerald");
        book1.setDescription("A novel set in the Roaring Twenties.");
        book1.setGenre("Fiction");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("1984");
        book2.setAuthor("George Orwell");
        book2.setDescription("A dystopian novel about totalitarianism.");
        book2.setGenre("Dystopian");
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("Pride and Prejudice");
        book3.setAuthor("Jane Austen");
        book3.setDescription("A romantic novel that critiques the British landed gentry.");
        book3.setGenre("Romance");
        bookRepository.save(book3);
    }

    @Test
    void searchByTitle_returnsMatchingBooks() throws Exception {
        mockMvc.perform(get("/api/books/search").param("title", "gatsby")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("The Great Gatsby"));
    }

    @Test
    void searchByAuthor_returnsMatchingBooks() throws Exception {
        mockMvc.perform(get("/api/books/search").param("author", "orwell")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].author").value("George Orwell"));
    }

    @Test
    void searchByGenre_returnsMatchingBooks() throws Exception {
        mockMvc.perform(get("/api/books/search").param("genre", "fiction")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].genre").value("Fiction"));
    }

    @Test
    void searchByCombinedParams_returnsMatchingBooks() throws Exception {
        mockMvc.perform(get("/api/books/search")
                .param("author", "austen")
                .param("genre", "romance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Pride and Prejudice"));
    }

    @Test
    void searchWithNoParams_returnsAllBooks() throws Exception {
        mockMvc.perform(get("/api/books/search")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void searchWithNoMatch_returnsEmptyList() throws Exception {
        mockMvc.perform(get("/api/books/search").param("title", "nonexistentbook")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
