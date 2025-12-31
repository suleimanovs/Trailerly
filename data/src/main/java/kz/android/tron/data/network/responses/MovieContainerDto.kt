package kz.android.tron.data.network.responses

import com.google.gson.annotations.SerializedName

data class MovieContainerDto(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieDto>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)