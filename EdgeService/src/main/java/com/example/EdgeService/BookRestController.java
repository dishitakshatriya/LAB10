/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EdgeService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author dishi
 */
@Controller
public class BookRestController {
    @Autowired
    private IBookClient client;
    
    @GetMapping("/books")
    @HystrixCommand(fallbackMethod = "fallbackBooks")

   /* public Collection<Book> getBooks() {
       return client.readBooks().getContent().stream().collect(Collectors.toList());
    }
    
    public Collection<Book> fallbackBooks() {
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setName("fall back book");
        book.setAuthor("fall back author");
        book.setGenre("fall back drama");
        books.add(book);
        return books;
    }*/
    
    public ModelAndView getBooks() {
       List<Book> books = client.readBooks().getContent().stream().collect(Collectors.toList());
       ModelAndView mv = new ModelAndView();
       mv.addObject("books", books);
       mv.setViewName("books");
       return mv;
    }
    
    public ModelAndView fallbackBooks() {
       List<Book> books = new ArrayList<>();
       Book book = new Book();
       book.setName("fall back book");
       book.setAuthor("fall back author");
       book.setGenre("fall back drama");
       books.add(book);
       ModelAndView mv = new ModelAndView();
       mv.addObject("books", books);
       mv.setViewName("books");
       return mv;
    }
}
