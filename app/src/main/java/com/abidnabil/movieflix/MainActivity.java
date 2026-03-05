package com.abidnabil.movieflix;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView moviesList;
    EditText searchText;

    ImageView favoriteListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesList = findViewById(R.id.recycler_view_movie_list);
        searchText = findViewById(R.id.text_view_search_name);
        favoriteListButton = findViewById(R.id.image_view_favorite_list);

        MovieAdapter movieAdapter = new MovieAdapter(AppData.movies);

        setupOnClickListeners(movieAdapter);


        moviesList.setAdapter(movieAdapter);
    }

    public void setupOnClickListeners(MovieAdapter movieAdapter) {
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

        favoriteListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviesList.getAdapter().notifyDataSetChanged();
    }
}