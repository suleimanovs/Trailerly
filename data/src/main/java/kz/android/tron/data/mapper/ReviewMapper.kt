package kz.android.tron.data.mapper

import kz.android.tron.data.network.dto.ReviewDto
import kz.android.tron.domain.model.Review

fun List<ReviewDto>?.reviewDtoToReview() = this?.map {
    Review(author = it.author.orEmpty(), content = it.content.orEmpty())
} ?: emptyList()
