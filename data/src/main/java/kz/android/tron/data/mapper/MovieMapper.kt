package kz.android.tron.data.mapper

import kz.android.tron.data.network.responses.MovieDto
import kz.android.tron.domain.mapper.BaseMapper
import kz.android.tron.domain.model.Movie

const val DEFAULT_ZERO_INT = 0
const val DEFAULT_ZERO_DOUBLE = 0.0

const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
const val SMALL_POSTER_SIZE = "w185"
const val BIG_POSTER_SIZE = "w780"

object MovieMapper : BaseMapper<MovieDto, Movie> {
    override fun map(source: MovieDto): Movie {
        return Movie(
            id = source.id ?: DEFAULT_ZERO_INT,
            backdropPath = BASE_POSTER_URL + BIG_POSTER_SIZE + source.backdropPath,
            genreId = source.genreIds ?: emptyList(),
            originalTitle = source.originalTitle.orEmpty(),
            overview = source.overview.orEmpty(),
            posterPath = BASE_POSTER_URL + SMALL_POSTER_SIZE + source.posterPath,
            releaseDate = source.releaseDate.orEmpty(),
            title = source.title.orEmpty(),
            voteAverage = (source.voteAverage ?: DEFAULT_ZERO_DOUBLE).doubleToInt(),
            bigPosterPath = BASE_POSTER_URL + BIG_POSTER_SIZE + source.posterPath,
            voteCount = source.voteCount ?: DEFAULT_ZERO_INT
        )
    }

    private fun Double.doubleToInt() = this.toString().replace(".", "").takeIf { it.length>=2 }?.take(2)?.toInt()?:0
}




