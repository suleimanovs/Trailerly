package kz.android.tron.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TrailerDto(
    @SerializedName("key")
    @Expose
    val key: String?,

    @SerializedName("name")
    @Expose
    val name: String?,
)
