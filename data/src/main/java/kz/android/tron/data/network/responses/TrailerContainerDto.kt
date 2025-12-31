package kz.android.tron.data.network.responses

import com.google.gson.annotations.SerializedName

data class TrailerContainerDto(
    @SerializedName("results") val results: List<TrailerDto>?
)