package kz.android.tron.data.network.dto

import com.google.gson.annotations.SerializedName


data class ReviewContainerDto(@SerializedName("results") val results: List<ReviewDto>?)