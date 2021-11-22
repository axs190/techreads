package com.manifestcorp.techreads.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.manifestcorp.techreads.repository.BookRepository;
import com.manifestcorp.techreads.model.Book;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class RestAPIController {


    @Autowired
    private BookRepository bookRepository;


    @GetMapping
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> bookDetail(@PathVariable("id")Long id){

        return new ResponseEntity(bookRepository.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        bookRepository.saveAndFlush(book);
        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    @PutMapping(("/{id}"))
    public ResponseEntity<Book> editBook(@RequestBody Book book,@PathVariable("id")Long id){
        book.setId(id);
        bookRepository.saveAndFlush(book);
        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id")Long id){
        bookRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }







}

