package com.manifestcorp.techreads;

import com.manifestcorp.techreads.controller.BookController;
import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;
    private Book testBook;
    @BeforeEach
    void setup(){
      testBook = new Book("Good Book 2");
      testBook.setId(3L);
      testBook.setAuthor("John Bookman");
      testBook.setRating("4");
      testBook.setCover("thumbnail");
    }


    @Test
    void testViewBook() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().isOk()).andExpect(model().attributeExists("books"))
                .andExpect(view().name("books"));
    }

    @Test
    void testAdd() throws Exception {

        mockMvc.perform(get("/books/add")).andExpect(status().isOk()).andExpect(model().attributeExists("bookForm"))
                .andExpect(view().name("add"));
    }
    @Test
    void testEdit() throws Exception {
        when(bookRepository.getById(testBook.getId())).thenReturn(testBook);
        mockMvc.perform(get("/books/edit/"+testBook.getId())).andExpect(status().isOk()).andExpect(model().attributeExists("bookForm"))
                .andExpect(view().name("edit"));
    }
    @Test
    void testDetailBook() throws Exception {
        when(bookRepository.getById(testBook.getId())).thenReturn(testBook);
        mockMvc.perform(get("/books/detail/" + testBook.getId())).andExpect(status().isOk())
                .andExpect(view().name("detail"));
    }


    @Test
    void testAddRedirect() throws Exception{
        when(bookRepository.saveAndFlush(testBook)).thenReturn(testBook);

        mockMvc.perform(post("/books").param("id", testBook.getId().toString()).param("title", testBook.getTitle()).param("author", testBook.getAuthor())
                        .param("rating", testBook.getRating()).param("cover", testBook.getCover()))
                .andExpect(status().is3xxRedirection());

        verify(bookRepository).saveAndFlush(testBook);
    }

    @Test
    void testRemoveRedirect() throws Exception{

        doNothing().when(bookRepository).deleteById(testBook.getId());

        mockMvc.perform(get("/books/delete/{id}", testBook.getId()))
                .andExpect(status().is3xxRedirection());

        verify(bookRepository).deleteById(testBook.getId());
    }

    @Test
    void testEditRedirect() throws Exception{
        when(bookRepository.saveAndFlush(testBook)).thenReturn(testBook);

        mockMvc.perform(post("/books/edit").param("id", testBook.getId().toString()).param("title", testBook.getTitle()).param("author", testBook.getAuthor())
                        .param("rating", testBook.getRating()).param("cover", testBook.getCover()))
                .andExpect(status().is3xxRedirection());

        verify(bookRepository).saveAndFlush(testBook);
    }






}
