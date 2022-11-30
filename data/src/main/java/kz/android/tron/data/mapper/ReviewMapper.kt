package kz.android.tron.data.mapper

import kz.android.tron.data.network.dto.ReviewDto
import kz.android.tron.domain.model.Review

fun List<ReviewDto>?.reviewDtoToReview(): List<Review> {
    return this?.map {
        Review(
            author = it.author ?: DEFAULT_EMPTY_STRING,
            content = it.content ?: DEFAULT_EMPTY_STRING
        )
    } ?: emptyList()
}