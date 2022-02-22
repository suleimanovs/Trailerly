package com.android.tron.data.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailerContainer(
    @SerializedName("results")
    @Expose
    val results: List<TrailerRemote>
)