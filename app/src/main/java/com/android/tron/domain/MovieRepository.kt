package com.android.tron.domain

import android.graphics.Movie
import androidx.lifecycle.LiveData

/**
 * Created by osmanboy on 2/17/2022.
 */

interface MovieRepository {

    fun getMovieList(): LiveData<List<Movie>>

    fun getMovieById(id:Int):LiveData<Movie>

    fun searchMovie(name:String):LiveData<Movie>

}