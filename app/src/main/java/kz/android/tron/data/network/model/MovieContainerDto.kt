package kz.android.tron.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * Created by osmanboy on 2/22/2022.
 */
data class MovieContainerDto(

    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val results: List<MovieDto>,

    @SerializedName("total_pages")
    @Expose
    val total_pages: Int,

    @SerializedName("total_results")
    @Expose
    val total_results: Int
)