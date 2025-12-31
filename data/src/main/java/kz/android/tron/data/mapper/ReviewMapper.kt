package kz.android.tron.data.mapper

import kz.android.tron.data.network.responses.ReviewDto
import kz.android.tron.domain.mapper.BaseMapper
import kz.android.tron.domain.model.Review

class ReviewMapper : BaseMapper<ReviewDto, Review> {

    override fun map(source: ReviewDto): Review {
        return Review(author = source.author.orEmpty(), content = source.content.orEmpty())
    }
}

