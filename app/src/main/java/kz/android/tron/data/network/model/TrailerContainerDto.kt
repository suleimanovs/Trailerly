package kz.android.tron.data.network.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * Created by osmanboy on 2/22/2022.
 */
data class TrailerContainerDto(
    @SerializedName("results")
    @Expose
    val results: List<TrailerDto>
)