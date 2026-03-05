package com.abidnabil.movieflix;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.net.URI;

public class MovieDetailActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    TextView movieText;
    ImageView favoriteImage;
    TextView movieDescription;
    ImageView backButton;
    Button goToYouTubeButton;
    Movie movieItem;
    RatingBar movieRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        findViews();
        //It can be used when app is not required to be published in play store
        //getLifecycle().addObserver(youTubePlayerView);

        Intent intent = getIntent();
        int movieId = intent.getIntExtra("MOVIE_ID", 0);
        for (Movie movie : AppData.movies) {

            if (movie.id == movieId) {
                movieItem = movie;
                break;
            }

        }

        displayMovie(movieItem);
        setupClickListeners(movieItem);


    }

    public void findViews() {
        youTubePlayerView = findViewById(R.id.youtube_player_movie_trailer);
        movieText = findViewById(R.id.text_view_movie_detail_name);
        favoriteImage = findViewById(R.id.image_view_movie_is_favorite);
        movieDescription = findViewById(R.id.text_view_long_description);
        backButton = findViewById(R.id.image_view_back_button);
        goToYouTubeButton = findViewById(R.id.button_youtube_play);

        movieRatingBar = findViewById(R.id.movie_rating_bar);
    }

    private void displayMovie(Movie movieItem) {
        if (movieItem != null) {

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(movieItem.trailerLink, 0);
                }
            });
            movieText.setText(movieItem.name);
            movieRatingBar.setRating(movieItem.rating);
            movieDescription.setText(movieItem.longDescription);
            if (AppData.favoriteMovies.contains(movieItem.id)) {
                favoriteImage.setImageResource(R.drawable.favourite_filled);
            }


        }

    }

    private void setupClickListeners(Movie movieItem) {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goToYouTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieItem.trailerLink)).setPackage("com.google.android.youtube");
                try {
                    startActivity(youtubeIntent);

                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieItem.trailerLink)));
                }
            }
        });
        favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppData.favoriteMovies.contains(movieItem.id)) {
                    AppData.favoriteMovies.remove(Integer.valueOf(movieItem.id));
                    favoriteImage.setImageResource(R.drawable.white_heart);
                } else {
                    AppData.favoriteMovies.add(movieItem.id);
                    favoriteImage.setImageResource(R.drawable.favourite_filled);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}