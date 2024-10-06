package com.example.movie_list_app.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private List<Movie> favoriteMovies;

    public User(String email) {
        this.email = email;
        this.favoriteMovies = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<Movie>getFavoriteMovies(){
        return this.favoriteMovies;
    }
    public void addFavoriteMovie(Movie movie){
        this.favoriteMovies.add(movie);
    }
    public void removeFavoriteMovie(Movie movie){
        this.favoriteMovies.remove(movie);
    }
}
