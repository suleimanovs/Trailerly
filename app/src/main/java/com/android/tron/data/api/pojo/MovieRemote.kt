package com.android.tron.data.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieRemote(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String?,

    @SerializedName("genre_ids")
    @Expose
    val genre_ids: List<Int>,

    @SerializedName("original_title")
    @Expose
    val original_title: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("poster_path")
    @Expose
    val poster_path: String,

    @SerializedName("release_date")
    @Expose
    val release_date: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("vote_average")
    @Expose
    val vote_average: Double,

    @SerializedName("vote_count")
    @Expose
    val vote_count: Int,

    var big_poster_path: String
) /*{

    fun copy(
        uniqueId: Int = this.uniqueId,
        id: Int = this.id,
        backdrop_path: String? = this.backdrop_path,
        genre_ids: List<Int> = this.genre_ids,
        original_title: String = this.original_title,
        overview: String = this.overview,
        poster_path: String = this.poster_path,
        release_date: String = this.release_date,
        title: String = this.title,
        vote_average: Double = this.vote_average,
        vote_count: Int = this.vote_count,
        big_poster_path: String = this.big_poster_path,
    ): MovieNetwork {
        return MovieNetwork(
            uniqueId,
            id,
            backdrop_path,
            genre_ids,
            original_title,
            overview,
            poster_path,
            release_date,
            title,
            vote_average,
            vote_count,
            big_poster_path,
        )
    }
}
*/