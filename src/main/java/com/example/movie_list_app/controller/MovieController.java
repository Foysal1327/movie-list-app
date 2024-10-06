package com.example.movie_list_app.controller;

import com.example.movie_list_app.model.Movie;
import com.example.movie_list_app.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private List<Movie> movies = Arrays.asList(
            new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Science Fiction",
                    "2010-07-16", 160000000),
            new Movie("The Dark Knight", Arrays.asList("Christian Bale", "Heath Ledger"), "Action", "2008-07-18",
                    185000000),
            new Movie("Interstellar", Arrays.asList("Matthew McConaughey", "Anne Hathaway"), "Science Fiction",
                    "2014-11-07", 165000000));
    // Simulate a single user
    private User user = new User("user@example.com");

    @GetMapping
    public List<Movie> getAllMovies() {
        return movies;
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam("q") String query) {
        return movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        movie.getCast().stream()
                                .anyMatch(castMember -> castMember.toLowerCase().contains(query.toLowerCase()))
                        ||
                        movie.getCategory().toLowerCase().contains(query.toLowerCase()))
                .sorted((m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle())).collect(Collectors.toList());
    }

    // Add a movie to favorites
    @PostMapping("/favorites/add")
    public String addFavoriteMovie(@RequestParam("title") String title) {
        Optional<Movie> movie = movies.stream().filter(m -> m.getTitle().equalsIgnoreCase(title)).findFirst();
        if (movie.isPresent()) {
            user.addFavoriteMovie(movie.get());
            return "Movie added to favorites!";
        } else {
            return "Movie not found!";
        }
    }

    // Remove a movie from favorites
    @PostMapping("/favorites/remove")
    public String removeFavoriteMovie(@RequestParam("title") String title) {
        Optional<Movie> movie = user.getFavoriteMovies().stream().filter(m -> m.getTitle().equalsIgnoreCase(title))
                .findFirst();
        if (movie.isPresent()) {
            user.removeFavoriteMovie(movie.get());
            return "Movie removed from favorites!";
        } else {
            return "Movie not found in favorites!";
        }
    }

    // View favorite movies
    @GetMapping("/favorites")
    public List<Movie> viewFavoriteMovies() {
        return user.getFavoriteMovies();
    }
    //Get movie details by title
    @GetMapping("/details")
    public ResponseEntity<?>getMovieDetails(@RequestParam("title") String title){
        Optional<Movie> movie = movies.stream().filter(m-> m.getTitle().equalsIgnoreCase(title)).findFirst();

        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
    }
}
