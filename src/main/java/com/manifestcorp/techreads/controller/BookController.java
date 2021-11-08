package com.manifestcorp.techreads.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;

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

}
