package kz.android.tron.data.mapper

import kz.android.tron.data.network.responses.TrailerDto
import kz.android.tron.domain.mapper.BaseMapper
import kz.android.tron.domain.model.Trailer

data object TrailerMapper : BaseMapper<TrailerDto, Trailer> {
    override fun map(source: TrailerDto): Trailer {
        return Trailer(
            key = source.key.orEmpty(),
            name = source.name.orEmpty()
        )
    }
}

