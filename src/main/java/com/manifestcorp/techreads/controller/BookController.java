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
    // Adds the attributes to the add page
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
    //removes the Book object my the Book ID
    public RedirectView removeBook(@PathVariable("id")Long id) {
        bookRepository.deleteById(id);
        return new RedirectView("/books");
    }

    //Shows the details for the book by showing a page with the attributes
    @RequestMapping(value = {"/detail/{id}"})
    public ModelAndView changeToDetail(@PathVariable("id")Long id) {
        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("book",bookRepository.getById(id));
        return mav;
    }
    @RequestMapping(value = {"/edit/{id}"})
    //sets up the form
    public ModelAndView editAttribute(@PathVariable("id")Long id) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("bookForm",bookRepository.getById(id));
        return mav;
    }
    //creates the button behavior
    @RequestMapping(value = "/edit", method=POST)
    public RedirectView editBook(Book book) {
        bookRepository.saveAndFlush(book);
        return new RedirectView("/books");
    }



}
