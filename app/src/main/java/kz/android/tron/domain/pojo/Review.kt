package kz.android.tron.domain.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author")
    @Expose
    val author: String,
    @SerializedName("content")
    @Expose
    val content: String,

)
