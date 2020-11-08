package dev.muathamer.assignment1.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MockInMemoryMovieDao implements MovieDao {

    private List<Movie> movies;

    public MockInMemoryMovieDao() {
        movies = getMockMovieData();
    }

    public MockInMemoryMovieDao(List<Movie> movieList) {
        movies = movieList;
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        return movies.stream().filter(
                movie -> movie.getTitle().toLowerCase().contains(title.trim().toLowerCase())
        ).collect(Collectors.toList());
    }

    @Override
    public List<Movie> searchMoviesByGenre(String genre) {
        return movies.stream().filter(
                movie -> movie.getGenre().name.contains(genre)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Movie> searchMoviesByYear(int year) {
        return movies.stream().filter(
                movie -> movie.getYear() == year
        ).collect(Collectors.toList());
    }

    private static List<Movie> getMockMovieData() {
        return Arrays.asList(
                new Movie("ChromeSkull: Laid to Rest 2", Movie.Genre.ADVENTURE, 1996),
                new Movie("Whatever It Takes", Movie.Genre.HORROR, 2008),
                new Movie("Mon Oncle (My Uncle)", Movie.Genre.HORROR, 2006),
                new Movie("Paisan (Paisà)", Movie.Genre.COMEDY, 2007),
                new Movie("Singing Marine, The", Movie.Genre.ADVENTURE, 1998),
                new Movie("Fine-Tune", Movie.Genre.DRAMA, 2008),
                new Movie("Shadrach", Movie.Genre.DRAMA, 2008),
                new Movie("Gable: The King Remembered", Movie.Genre.ADVENTURE, 2005),
                new Movie("Time Without Pity", Movie.Genre.SCIENCE_FICTION, 2011),
                new Movie("The Little Kidnappers", Movie.Genre.ACTION, 1988),
                new Movie("Unlawful Killing", Movie.Genre.THRILLER, 1999),
                new Movie("Ju-on: Black Ghost", Movie.Genre.THRILLER, 2006),
                new Movie("Statues Also Die (Statues meurent aussi, Les)", Movie.Genre.DRAMA, 1993),
                new Movie("Night Court", Movie.Genre.THRILLER, 2005),
                new Movie("Rosalie Goes Shopping", Movie.Genre.THRILLER, 2002),
                new Movie("Stardust Memories", Movie.Genre.COMEDY, 1995),
                new Movie("The Horseplayer", Movie.Genre.HORROR, 1992),
                new Movie("Head Over Heels (De Pernas pro Ar)", Movie.Genre.HORROR, 1986),
                new Movie("The Story of Alexander Graham Bell", Movie.Genre.HORROR, 1990),
                new Movie("Pervert''s Guide to Cinema, The", Movie.Genre.HORROR, 1999),
                new Movie("Affairs of Anatol, The", Movie.Genre.SCIENCE_FICTION, 1988),
                new Movie("Dear Wendy", Movie.Genre.ACTION, 2004),
                new Movie("Fire and Ice", Movie.Genre.HORROR, 1989),
                new Movie("Max Payne", Movie.Genre.SCIENCE_FICTION, 2010),
                new Movie("What Women Want (a.k.a. I Know a Woman''s Heart)", Movie.Genre.HORROR, 1995),
                new Movie("Chances Are", Movie.Genre.ADVENTURE, 1993),
                new Movie("Yatterman (Yattâman)", Movie.Genre.HORROR, 2003),
                new Movie("A Story of Children and Film", Movie.Genre.COMEDY, 2009),
                new Movie("Waiting for Forever", Movie.Genre.ACTION, 1996),
                new Movie("After Midnight", Movie.Genre.SCIENCE_FICTION, 1992),
                new Movie("Made in Hong Kong (Xiang Gang zhi zao)", Movie.Genre.COMEDY, 2011),
                new Movie("Poetic Justice", Movie.Genre.ACTION, 2000),
                new Movie("It Had to Be You", Movie.Genre.THRILLER, 1992),
                new Movie("Monsignor", Movie.Genre.ADVENTURE, 2006),
                new Movie("No One Dies in Lily Dale", Movie.Genre.DRAMA, 1988),
                new Movie("Faster Pussycat! Kill! Kill!", Movie.Genre.ADVENTURE, 2012),
                new Movie("Little Princess, The", Movie.Genre.ACTION, 2008),
                new Movie("Star Wars: The Clone Wars", Movie.Genre.THRILLER, 1994),
                new Movie("At the River I Stand", Movie.Genre.COMEDY, 2011),
                new Movie("Big Stampede, The", Movie.Genre.ACTION, 2010),
                new Movie("King and the Clown, The (Wang-ui namja)", Movie.Genre.ACTION, 2004),
                new Movie("Your Friends and Neighbors", Movie.Genre.HORROR, 2004),
                new Movie("Fast Lane", Movie.Genre.COMEDY, 1998),
                new Movie("League of Ordinary Gentlemen, A", Movie.Genre.HORROR, 2010),
                new Movie("Kobe Doin'' Work", Movie.Genre.THRILLER, 1991),
                new Movie("Smiley''s People", Movie.Genre.SCIENCE_FICTION, 2011),
                new Movie("Anne Frank Remembered", Movie.Genre.THRILLER, 2011),
                new Movie("Anita", Movie.Genre.HORROR, 2009),
                new Movie("Food, Inc.", Movie.Genre.DRAMA, 2005),
                new Movie("Tap", Movie.Genre.THRILLER, 2006),
                new Movie("Butterflies Are Free", Movie.Genre.ADVENTURE, 1989),
                new Movie("Pee-wee''s Big Adventure", Movie.Genre.ADVENTURE, 1993),
                new Movie("Way Out West", Movie.Genre.THRILLER, 2007),
                new Movie("Santa Sangre", Movie.Genre.ACTION, 1964),
                new Movie("Redirected", Movie.Genre.ACTION, 2010),
                new Movie("Color of Night", Movie.Genre.DRAMA, 1993),
                new Movie("Don''t Look Now", Movie.Genre.ADVENTURE, 2009),
                new Movie("Busting", Movie.Genre.ADVENTURE, 1997),
                new Movie("Inspector General, The", Movie.Genre.COMEDY, 2007),
                new Movie("Outside the Law (Hors-la-loi)", Movie.Genre.ACTION, 1996),
                new Movie("Adam''s Rib", Movie.Genre.THRILLER, 2006),
                new Movie("Cold Comfort Farm", Movie.Genre.HORROR, 2007),
                new Movie("New Land, The (Nybyggarna)", Movie.Genre.ACTION, 1998),
                new Movie("Hand in Hand", Movie.Genre.DRAMA, 1997),
                new Movie("DEFCON: The Documentary", Movie.Genre.COMEDY, 1987),
                new Movie("Leave Her to Heaven", Movie.Genre.SCIENCE_FICTION, 1996),
                new Movie("Devil''s Own, The", Movie.Genre.ACTION, 1987),
                new Movie("Alice Doesn''t Live Here Anymore", Movie.Genre.ACTION, 1993),
                new Movie("Frozen Hell (Jäämarssi) ", Movie.Genre.ACTION, 2005),
                new Movie("They Call Me Bruce? (a.k.a. A Fistful of Chopsticks)", Movie.Genre.COMEDY, 2012),
                new Movie("Mummy: Tomb of the Dragon Emperor, The", Movie.Genre.HORROR, 2006),
                new Movie("Bad Asses (Bad Ass 2)", Movie.Genre.HORROR, 2005),
                new Movie("Asterix and the Gauls (Astérix le Gaulois)", Movie.Genre.DRAMA, 1993),
                new Movie("Comrade Pedersen (Gymnaslærer Pedersen)", Movie.Genre.THRILLER, 2003),
                new Movie("Get Hard", Movie.Genre.ACTION, 1990),
                new Movie("The Great Alligator", Movie.Genre.ADVENTURE, 1983),
                new Movie("Split, The", Movie.Genre.COMEDY, 1999),
                new Movie("Village People Radio Show (Apa khabar orang kampung)", Movie.Genre.COMEDY, 2002),
                new Movie("Night Tide", Movie.Genre.ACTION, 2010),
                new Movie("Lodger: A Story of the London Fog, The", Movie.Genre.DRAMA, 2012),
                new Movie("Lady for a Day", Movie.Genre.DRAMA, 2006),
                new Movie("Alps (Alpeis)", Movie.Genre.SCIENCE_FICTION, 2012),
                new Movie("20 Dates", Movie.Genre.HORROR, 2010),
                new Movie("My Man and I", Movie.Genre.HORROR, 1986),
                new Movie("This Is My Life", Movie.Genre.ADVENTURE, 2002),
                new Movie("Major Movie Star", Movie.Genre.THRILLER, 1993),
                new Movie("Those Daring Young Men in Their Jaunty Jalopies", Movie.Genre.HORROR, 2005),
                new Movie("Diary for Timothy, A", Movie.Genre.HORROR, 2006),
                new Movie("Ed''s Next Move", Movie.Genre.ACTION, 2010),
                new Movie("Saddest Music in the World, The", Movie.Genre.ACTION, 1992),
                new Movie("My Best Girl", Movie.Genre.HORROR, 2007),
                new Movie("End of Violence, The", Movie.Genre.SCIENCE_FICTION, 1997),
                new Movie("$9.99", Movie.Genre.SCIENCE_FICTION, 2000)
        );
    }
}
