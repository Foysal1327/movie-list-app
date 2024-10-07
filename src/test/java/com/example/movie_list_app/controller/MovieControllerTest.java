package com.example.movie_list_app.controller;

import com.example.movie_list_app.model.Movie;
import com.example.movie_list_app.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class MovieControllerTest {
    @InjectMocks
    private MovieController movieController;

    @Mock
    private User user;

    private List<Movie> mockMovies;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMovies = Arrays.asList(
                new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Science Fiction", "2010-07-16", 160000000),
                new Movie("The Dark Knight", Arrays.asList("Christian Bale", "Heath Ledger"), "Action", "2008-07-18", 185000000)
        );
    }

    @Test
    void testGetAllMovies() {
        List<Movie> movies = movieController.getAllMovies();
        assertNotNull(movies);
        assertEquals(3, movies.size());  // Assuming there are 3 movies in total
    }

    @Test
    void testSearchMovies() {
        List<Movie> result = movieController.searchMovies("Inception");
        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).getTitle());
    }

    @Test
    void testAddFavoriteMovie() {
        when(user.getFavoriteMovies()).thenReturn(mockMovies);

        String response = movieController.addFavoriteMovie("Inception");

        assertEquals("Movie added to favorites!", response);
        verify(user, times(1)).addFavoriteMovie(any(Movie.class));
    }

    @Test
    void testRemoveFavoriteMovie() {
        when(user.getFavoriteMovies()).thenReturn(mockMovies);

        String response = movieController.removeFavoriteMovie("Inception");

        assertEquals("Movie removed from favorites!", response);
        verify(user, times(1)).removeFavoriteMovie(any(Movie.class));
    }

    @Test
    void testViewFavoriteMovies() {
        when(user.getFavoriteMovies()).thenReturn(mockMovies);

        List<Movie> favorites = movieController.viewFavoriteMovies();

        assertNotNull(favorites);
        assertEquals(2, favorites.size());
    }

    @Test
    void testGetMovieDetails_success() {
        ResponseEntity<?> response = movieController.getMovieDetails("Inception");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Movie);
        assertEquals("Inception", ((Movie) response.getBody()).getTitle());
    }

    @Test
    void testGetMovieDetails_notFound() {
        ResponseEntity<?> response = movieController.getMovieDetails("NonExistentMovie");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Movie not found", response.getBody());
    }
}
