package com.android.tron.data.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreRemote(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String
)