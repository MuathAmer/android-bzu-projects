package dev.muathamer.assignment1.model;

public class Movie {

    private String title;
    private Genre genre;
    private int year;

    public Movie(String title, Genre genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%s, released in %d, %s movie.", title, year, genre.name);
    }

    public enum Genre {
        ACTION("Action"),
        ADVENTURE("Adventure"),
        COMEDY("Comedy"),
        DRAMA("Drama"),
        HORROR("Horror"),
        SCIENCE_FICTION("Science Fiction"),
        THRILLER("Thriller");

        public final String name;

        Genre(String name){
            this.name = name;
        }
    }
}
