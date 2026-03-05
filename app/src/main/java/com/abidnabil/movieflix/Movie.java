package com.abidnabil.movieflix;

import androidx.annotation.DrawableRes;

public class Movie {


    int id;
    String name;
    String description;
    String longDescription;
    float rating;
    String trailerLink;
    int poster;

    Movie(int id, String name, String description, String longDescription, float rating, String trailerLink, @DrawableRes int poster) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.longDescription=longDescription;
        this.rating = rating;
        this.trailerLink = trailerLink;
        this.poster = poster;
    }


}
