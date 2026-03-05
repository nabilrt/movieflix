package com.abidnabil.movieflix;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView moviesList;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesList = findViewById(R.id.recycler_view_movie_list);
        searchText = findViewById(R.id.text_view_search_name);

        MovieAdapter movieAdapter = new MovieAdapter(AppData.movies);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key = s.toString().toLowerCase();
                ArrayList<Movie> results = new ArrayList<>();

                for (int i = 0; i < AppData.movies.length; i++) {

                    if (AppData.movies[i].name.toLowerCase().contains(key)) {
                        results.add(AppData.movies[i]);
                    }

                }

                Movie[] searchResults = results.toArray(new Movie[0]);

                if (key.isEmpty()) {
                    movieAdapter.movies = AppData.movies;
                } else {
                    movieAdapter.movies = searchResults;
                }
                movieAdapter.notifyDataSetChanged();

            }
        });
        moviesList.setAdapter(movieAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviesList.getAdapter().notifyDataSetChanged();
    }
}