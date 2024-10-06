package com.example.movie_list_app.controller;

import com.example.movie_list_app.model.Movie;
import com.example.movie_list_app.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private List<Movie> movies = Arrays.asList(
            new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Science Fiction", "2010-07-16", 160000000),
            new Movie("The Dark Knight", Arrays.asList("Christian Bale", "Heath Ledger"), "Action", "2008-07-18", 185000000),
            new Movie("Interstellar", Arrays.asList("Matthew McConaughey", "Anne Hathaway"), "Science Fiction", "2014-11-07", 165000000)
    );
    // Simulate a single user
    private User user = new User("user@example.com");

    @GetMapping
    public List<Movie>getAllMovies(){
        return movies;
    }
    @GetMapping("/search")
    public List<Movie>searchMovies(@RequestParam("q") String query){
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        movie.getCast().stream().anyMatch(castMember-> castMember.toLowerCase().contains(query.toLowerCase())) ||
                        movie.getCategory().toLowerCase().contains(query.toLowerCase())
                ).sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle())).collect(Collectors.toList());
    }
}
