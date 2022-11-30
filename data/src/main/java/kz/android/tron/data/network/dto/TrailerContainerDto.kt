package kz.android.tron.data.network.dto


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TrailerContainerDto(
    @Expose
    @SerializedName("results")
    val results: List<TrailerDto>?
)