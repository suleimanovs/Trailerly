package kz.android.tron.data.network.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * Created by osmanboy on 2/22/2022.
 */
data class MovieDto(

    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("backdrop_path")
    @Expose
    val backdrop_path: String?,

    @SerializedName("genre_ids")
    @Expose
    val genre_ids: List<Int>?,

    @SerializedName("original_title")
    @Expose
    val original_title: String?,

    @SerializedName("overview")
    @Expose
    val overview: String?,

    @SerializedName("poster_path")
    @Expose
    val poster_path: String?,

    @SerializedName("release_date")
    @Expose
    val release_date: String?,

    @SerializedName("title")
    @Expose
    val title: String?,

    @SerializedName("vote_average")
    @Expose
    val vote_average: Double?,

    @SerializedName("vote_count")
    @Expose
    val vote_count: Int?,

)