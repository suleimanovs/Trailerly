package kz.android.tron.data.network.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
/**
 * Created by osmanboy on 2/22/2022.
 */
data class ReviewDto(
    @SerializedName("author")
    @Expose
    val author: String?,
    @SerializedName("content")
    @Expose
    val content: String?,

)
