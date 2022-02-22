package com.android.tron.domain.pojo

/**
 * Created by osmanboy on 2/17/2022.
 */
data class Movie(

    val id: Int,

    val backdrop_path: String?,

    val genre_ids: List<Int>,

    val original_title: String,

    val overview: String,

    val poster_path: String,

    val release_date: String,

    val title: String,

    val vote_average: Double,

    val vote_count: Int,

    var big_poster_path: String
)