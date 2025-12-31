package kz.android.tron.data.network.responses


import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id")  val id: Int?,
    @SerializedName("backdrop_path")  val backdropPath: String?,
    @SerializedName("genre_ids")  val genreIds: List<Int>?,
    @SerializedName("original_title")  val originalTitle: String?,
    @SerializedName("overview")  val overview: String?,
    @SerializedName("poster_path")  val posterPath: String?,
    @SerializedName("release_date")  val releaseDate: String?,
    @SerializedName("title")  val title: String?,
    @SerializedName("vote_average")  val voteAverage: Double?,
    @SerializedName("vote_count")  val voteCount: Int?,
)