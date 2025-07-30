package kz.android.tron.data.mapper

import kz.android.tron.data.network.responses.ReviewDto
import kz.android.tron.domain.mapper.BaseMapper
import kz.android.tron.domain.model.Review

data object ReviewListMapper : BaseMapper<List<ReviewDto>?, List<Review>> {
    override fun map(source: List<ReviewDto>?): List<Review> {
        return source?.map { ReviewMapper().map(it) } ?: emptyList()
    }
}