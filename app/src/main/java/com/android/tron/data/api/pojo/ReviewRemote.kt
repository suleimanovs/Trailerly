package com.android.tron.data.api.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewRemote(
    @SerializedName("author")
    @Expose
    val author: String,
    @SerializedName("content")
    @Expose
    val content: String,

)
