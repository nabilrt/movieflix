package com.abidnabil.movieflix;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteListActivity extends AppCompatActivity {
    RecyclerView favoriteList;
    ImageView backButton;
    MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        favoriteList = findViewById(R.id.recycler_view_favorite_movie_list);
        backButton=findViewById(R.id.image_view_back_from_favorites);

        movieAdapter = new MovieAdapter(favoriteMovieList());

        favoriteList.setAdapter(movieAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public Movie[] favoriteMovieList() {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        for (int i = 0; i < AppData.movies.length; i++) {
            if (AppData.favoriteMovies.contains(AppData.movies[i].id)) {
                movieArrayList.add(AppData.movies[i]);
            }
        }

        return movieArrayList.toArray(new Movie[0]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieAdapter.movies=favoriteMovieList();
        movieAdapter.notifyDataSetChanged();
    }
}