package kz.android.tron.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TrailerDto(
    @SerializedName("key")
    val key: String?,

    @SerializedName("name")
    val name: String?,
)
