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
public class MovieRestController {
    @Autowired
    private iMovieClient client;
    
    /*public MovieRestController(iMovieClient _client) {
        this.client = _client;
    }*/
    @GetMapping("/movies")
    @HystrixCommand(fallbackMethod = "fallbackMovies")

    /*public Collection<Movie> getMovies() {
       return client.readMovies().getContent().stream().collect(Collectors.toList());
    }
    
    public Collection<Movie> fallbackMovies() {
        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        movie.setName("fall back movie");
        movie.setRating(10);
        movies.add(movie);
        return movies;
    }*/
    
    public ModelAndView getMovies() {
       List<Movie> movies = client.readMovies().getContent().stream().collect(Collectors.toList());
       ModelAndView mv = new ModelAndView();
       mv.addObject("movies", movies);
       mv.setViewName("movies");
       return mv;
    }
    
    public ModelAndView fallbackMovies() {
       List<Movie> movies = new ArrayList<>();
       Movie movie = new Movie();
       movie.setName("fall back movie");
       movie.setRating(10);
       movies.add(movie);
       ModelAndView mv = new ModelAndView();
       mv.addObject("movies", movies);
       mv.setViewName("movies");
       return mv;
    }
    
    
}
