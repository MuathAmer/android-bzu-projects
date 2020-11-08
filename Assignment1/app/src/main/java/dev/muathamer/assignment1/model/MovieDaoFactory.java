package dev.muathamer.assignment1.model;

import java.util.List;

public class MovieDaoFactory {
    private static MovieDao movieDao;

    public static MovieDao getMovieDao() {
        if(movieDao == null) movieDao = new MockInMemoryMovieDao();
        return movieDao;
    }

    public static MovieDao getMovieDaoFromList(List<Movie> movieList){
        return new MockInMemoryMovieDao(movieList);
    }
}
