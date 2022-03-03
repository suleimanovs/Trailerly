package kz.android.tron.data.network.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * Created by osmanboy on 2/22/2022.
 */
data class ReviewDto(

    @Expose
    @SerializedName("author")
    val author: String?,

    @Expose
    @SerializedName("content")
    val content: String?,

)
