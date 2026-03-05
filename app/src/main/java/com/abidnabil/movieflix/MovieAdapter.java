package com.abidnabil.movieflix;

import static com.abidnabil.movieflix.AppData.favoriteMovies;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    Movie[] movies;

    MovieAdapter(Movie[] movies) {
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies[position]);

    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;
        TextView movieName;
        TextView movieDescription;
        RatingBar movieRating;
        ImageView isFavorite;


        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.text_view_movie_name);
            moviePoster = itemView.findViewById(R.id.image_view_movie_poster);
            movieDescription = itemView.findViewById(R.id.text_view_movie_description);
            movieRating = itemView.findViewById(R.id.rating_bar_movie_rating);
            isFavorite = itemView.findViewById(R.id.image_view_is_favorite);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getBindingAdapterPosition();
                    if (adapterPosition == RecyclerView.NO_POSITION) return;
                    Intent movieDetailIntent = new Intent(v.getContext(), MovieDetailActivity.class);
                    movieDetailIntent.putExtra("MOVIE_ID", movies[adapterPosition].id);
                    v.getContext().startActivity(movieDetailIntent);

                }
            });
            isFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getBindingAdapterPosition();
                    if (adapterPosition == RecyclerView.NO_POSITION) return;
                    if(favoriteMovies.contains(movies[adapterPosition].id)){
                        favoriteMovies.remove(Integer.valueOf(movies[adapterPosition].id));
                        isFavorite.setImageResource(R.drawable.white_heart);

                    }else{
                        favoriteMovies.add(movies[adapterPosition].id);
                        isFavorite.setImageResource(R.drawable.favourite_filled);

                    }
                }
            });
        }

        public void bind(Movie movie) {
            moviePoster.setImageResource(movie.poster);
            movieName.setText(movie.name);
            movieDescription.setText(movie.description);
            movieRating.setRating(movie.rating);
            if (favoriteMovies.contains(movie.id)) {
                isFavorite.setImageResource(R.drawable.favourite_filled);
            }else{
                isFavorite.setImageResource(R.drawable.white_heart);
            }

        }
    }
}
