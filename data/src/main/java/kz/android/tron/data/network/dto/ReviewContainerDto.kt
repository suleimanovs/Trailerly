package kz.android.tron.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ReviewContainerDto(
    @SerializedName("results")
    @Expose
    val results: List<ReviewDto>?,
)