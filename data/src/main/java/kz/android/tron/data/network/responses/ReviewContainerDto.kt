package kz.android.tron.data.network.responses

import com.google.gson.annotations.SerializedName

data class ReviewContainerDto(@SerializedName("results") val results: List<ReviewDto>?)