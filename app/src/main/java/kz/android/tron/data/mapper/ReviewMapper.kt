package kz.android.tron.data.mapper

import kz.android.tron.data.network.model.ReviewDto
import kz.android.tron.domain.pojo.Review

/**
 * Created by osmanboy on 3/1/2022.
 */

fun List<ReviewDto>.reviewDtoToReview(): List<Review> {
    return this.map {
        Review(
            author = it.author ?: DEFAULT_EMPTY_STRING,
            content = it.content ?: DEFAULT_EMPTY_STRING
        )
    }
}