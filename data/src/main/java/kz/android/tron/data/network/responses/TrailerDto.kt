package kz.android.tron.data.network.responses

import com.google.gson.annotations.SerializedName

data class TrailerDto(
    @SerializedName("key") val key: String?,
    @SerializedName("name") val name: String?,
)
