package com.example.tmdbAPI;


import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";


    @RequestMapping("/")public String home(Model model){
        return "home";
    }

    @RequestMapping("/medium-popular-long-name")public String mediumPopularLongName(Model model){
        return "medium-popular-long-name";
    }


    @RequestMapping("/now-playing")
    public String nowPlaying(Model model) {
        String movies = getMovies(API_URL);
        model.addAttribute("movies", movies);

        return "now-playing";
    }


    public static String getMovies(String route){

        List<Movie> movies;
        RestTemplate restTemplate = new RestTemplate();

        ResultsPage objectGetter = new ResultsPage();
        try{

            objectGetter = restTemplate.getForObject(API_URL, ResultsPage.class);
        } catch(SpelEvaluationException ex){
            System.out.println(ex);
        }

        movies = objectGetter.getResults();
        return String.valueOf(movies);

    }


}
