package kz.android.tron.data.network.paging

import kz.android.tron.data.mapper.TrailerListMapper
import kz.android.tron.data.network.retrofit.ApiService
import kz.android.tron.domain.model.Trailer
import kz.android.tron.domain.repository.BaseRepository
import kz.android.tron.domain.repository.mappedApiCall

class TrailerListDataSource(private val service: ApiService, private val id: Int) : BasePagingSource<Trailer>(), BaseRepository {

    override suspend fun mapData(): Result<List<Trailer>> {
        return mappedApiCall(TrailerListMapper) { service.getMovieTrailersById(id = id).results }
    }
}

