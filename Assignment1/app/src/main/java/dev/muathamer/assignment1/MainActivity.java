package dev.muathamer.assignment1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import dev.muathamer.assignment1.model.Movie;
import dev.muathamer.assignment1.model.MovieDao;
import dev.muathamer.assignment1.model.MovieDaoFactory;

public class MainActivity extends AppCompatActivity {

    private EditText titleSearchEditText;
    private EditText moviesResultsEditText;
    private LinearLayout searchOptionsLayout;
    private Spinner searchGenreSpinner;
    private EditText yearEditText;

    private MovieDao movieDao = MovieDaoFactory.getMovieDao();

    private Function<String, List<Movie>> searchFunction;
    private String searchGenres = "All movie genres";
    private boolean searchOptionsVisibility = false;
    private CheckBox yearEnableCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleSearchEditText = findViewById(R.id.titleSearchEditText);
        moviesResultsEditText = findViewById(R.id.moviesResultsEditText);
        searchOptionsLayout = findViewById(R.id.searchOptionsLayout);
        searchGenreSpinner = findViewById(R.id.searchGenreSpinner);
        yearEditText = findViewById(R.id.yearEditText);
        yearEnableCheckBox = findViewById(R.id.yearEnableCheckBox);

        searchOptionsLayout.setVisibility(View.GONE);
        populateGenreSpinner();
        initializeTitleEditText();
        initializeYearViews();
    }

    private void initializeYearViews() {
        yearEnableCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            yearEditText.setEnabled(isChecked);
            search(null);
        });

        yearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initializeTitleEditText() {
        titleSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void populateGenreSpinner() {
        List<Movie.Genre> genresList = Arrays.asList(Movie.Genre.values());
        List<String> genresTitlesList = genresList.stream().map(genre -> genre.name).collect(Collectors.toList());
        genresTitlesList.add(0, "All genres");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genresTitlesList);

        searchGenreSpinner.setAdapter(adapter);

        searchGenreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void toggleSearchOptionsVisibility(View view) {
        searchOptionsVisibility = !searchOptionsVisibility;
        searchOptionsLayout.setVisibility(searchOptionsVisibility ? View.VISIBLE : View.GONE);
    }

    public void search(View view) {
        MovieDao moviesQuery = movieDao;

        int genreSelectionIndex = searchGenreSpinner.getSelectedItemPosition();
        System.out.println(genreSelectionIndex);
        if (genreSelectionIndex != 0) {
            String genreName = searchGenreSpinner.getSelectedItem().toString();
            List<Movie> movies = moviesQuery.searchMoviesByGenre(genreName);
            moviesQuery = MovieDaoFactory.getMovieDaoFromList(movies);
        }

        if (yearEnableCheckBox.isChecked() && !yearEditText.getText().toString().isEmpty()) {
            int movieYear = Integer.parseInt(yearEditText.getText().toString());
            List<Movie> movies = moviesQuery.searchMoviesByYear(movieYear);
            moviesQuery = MovieDaoFactory.getMovieDaoFromList(movies);
        }

        String movieTitle = titleSearchEditText.getText().toString();
        List<Movie> movies = moviesQuery.searchMoviesByTitle(movieTitle);
        populateResult(movies);
    }

    private void populateResult(List<Movie> movies) {
        String result = movies.stream().map(Movie::toString).collect(Collectors.joining("\n\n• "));
        moviesResultsEditText.setText(result.isEmpty() ? "No matching movies." : movies.size() + " matching movies:\n\n• " + result);
    }
}