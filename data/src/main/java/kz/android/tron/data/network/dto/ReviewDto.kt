package kz.android.tron.data.network.dto


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewDto(

    @Expose
    @SerializedName("author")
    val author: String?,

    @Expose
    @SerializedName("content")
    val content: String?,

)
