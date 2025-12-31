package kz.android.tron.data.mapper

import kz.android.tron.data.network.responses.TrailerDto
import kz.android.tron.domain.mapper.BaseMapper
import kz.android.tron.domain.model.Trailer

data object TrailerListMapper : BaseMapper<List<TrailerDto>?, List<Trailer>> {
    override fun map(source: List<TrailerDto>?): List<Trailer> {
        return source?.map(TrailerMapper::map).orEmpty()
    }
}