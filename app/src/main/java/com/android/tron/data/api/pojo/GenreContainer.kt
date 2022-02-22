package com.android.tron.data.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreContainer(
    @SerializedName("genres")
    @Expose
    val genres: List<GenreRemote>
)