package kz.android.tron.data.mapper

import kz.android.tron.data.network.dto.TrailerDto
import kz.android.tron.domain.model.Trailer

fun List<TrailerDto>?.trailerDtoToTrailer() = this?.map {
    Trailer(key = it.key.orEmpty(), name = it.name.orEmpty())
} ?: emptyList()

