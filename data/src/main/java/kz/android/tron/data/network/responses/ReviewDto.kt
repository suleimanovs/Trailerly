package kz.android.tron.data.network.responses


import com.google.gson.annotations.SerializedName

data class ReviewDto(

    @SerializedName("author")
    val author: String?,

    @SerializedName("content")
    val content: String?,

)
