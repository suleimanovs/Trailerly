package kz.android.data.mapper

import kz.android.data.network.model.TrailerDto
import kz.android.tron.domain.model.Trailer

/**
 * Created by osmanboy on 3/1/2022.
 */

fun List<TrailerDto>?.trailerDtoToTrailer(): List<Trailer> {
    return this?.map {
        Trailer(
            key = it.key ?: DEFAULT_EMPTY_STRING,
            name = it.name ?: DEFAULT_EMPTY_STRING
        )
    }?: emptyList()
}
