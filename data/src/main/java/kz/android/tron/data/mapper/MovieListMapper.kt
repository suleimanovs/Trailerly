package kz.android.tron.data.mapper

import kz.android.tron.data.network.responses.MovieDto
import kz.android.tron.domain.mapper.BaseMapper
import kz.android.tron.domain.model.Movie

object MovieListMapper : BaseMapper<List<MovieDto>?, List<Movie>> {
    override fun map(source: List<MovieDto>?): List<Movie> {
        return source?.map(MovieMapper::map).orEmpty()
    }
}