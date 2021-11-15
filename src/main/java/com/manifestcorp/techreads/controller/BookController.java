package com.manifestcorp.techreads.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @RequestMapping({"","/"})
    public ModelAndView books() {
        ModelAndView mav = new ModelAndView("books");

        List<Book> books = bookRepository.findAll();
        mav.addObject("books", books);
        return mav;
    }

    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("bookForm", new Book());
        return"add";
    }
    @RequestMapping(value = {"", "/"}, method=POST)
    public RedirectView addBook(Book book) {
        bookRepository.saveAndFlush(book);
        return new RedirectView("/books");
    }

    @GetMapping(value = {"/delete/{id}"})
    public RedirectView removeBook(@PathVariable("id")Long id) {
        bookRepository.deleteById(id);
        return new RedirectView("/books");
    }


}
