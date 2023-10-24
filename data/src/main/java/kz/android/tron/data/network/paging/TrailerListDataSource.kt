package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.trailerDtoToTrailer
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Trailer

class TrailerListDataSource(private val service: ApiService, val id: Int) : BasePagingSource<Trailer>() {

    override suspend fun mapData(): List<Trailer> {
        val response = service.getMovieTrailersById(id = id)
        return response.body()?.results.trailerDtoToTrailer()
    }
}

