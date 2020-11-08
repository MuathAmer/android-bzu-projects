package dev.muathamer.assignment1.model;

import java.util.List;

public interface MovieDao {

    public List<Movie> searchMoviesByTitle(String title);

    public List<Movie> searchMoviesByGenre(String genre);

    public List<Movie> searchMoviesByYear(int year);

}
