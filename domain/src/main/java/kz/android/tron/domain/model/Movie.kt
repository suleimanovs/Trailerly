package kz.android.tron.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by osmanboy on 2/17/2022.
 */

@Parcelize
data class Movie(

    val id: Int,

    val backdrop_path: String,

    val genre_id: List<Int>,

    val original_title: String,

    val overview: String,

    val poster_path: String,

    val release_date: String,

    val title: String,

    val vote_average: Int,

    val vote_count: Int,

    var big_poster_path: String

):Parcelable