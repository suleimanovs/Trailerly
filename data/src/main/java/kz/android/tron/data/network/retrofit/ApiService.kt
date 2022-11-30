package kz.android.tron.data.network.retrofit

import kz.android.tron.data.network.dto.MovieContainerDto
import kz.android.tron.data.network.dto.MovieDto
import kz.android.tron.data.network.dto.ReviewContainerDto
import kz.android.tron.data.network.dto.TrailerContainerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(BASE_URL_MOVIES)
    suspend fun getAllMovies(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_SORT) sortBy: String,
        @Query(PARAMS_PAGE) page: Int
    ): Response<MovieContainerDto>

    @GET(BASE_URL_VIDEOS)
    suspend fun getMovieTrailersById(
        @Path("id") id: Int,
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
    ): Response<TrailerContainerDto>

    @GET(BASE_URL_REVIEWS)
    suspend fun getMovieReviewsById(
        @Path("id") id: Int,
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
    ): Response<ReviewContainerDto>


    @GET(BASE_URL_SEARCH)
    suspend fun searchMovie(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_QUERY) query: String
    ): Response<MovieContainerDto>


    @GET(BASE_URL_POPULAR)
    suspend fun getPopularMovies(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_PAGE) page: Int
    ): Response<MovieContainerDto>


    @GET(BASE_URL_TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_PAGE) page: Int
    ): Response<MovieContainerDto>


    @GET(BASE_URL_MOVIE)
    suspend fun getMovieById(
        @Path("id") id: Int,
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
    ): MovieDto

    @GET(BASE_URL_MOVIES)
    suspend fun getMoviesByGenre(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_PAGE) page: Int? = null,
        @Query(PARAMS_GENRE) genreId: Int? = null,
    ): Response<MovieContainerDto>


    companion object {

        const val API_KEY = "91980027d393239a4d9b9b8da5a39c14"
        const val LANGUAGE_VALUE = "ru-RU"

        const val BASE_URL_SEARCH = "search/movie"
        const val BASE_URL_MOVIES = "discover/movie"
        const val BASE_URL_REVIEWS = "movie/{id}/reviews"
        const val BASE_URL_VIDEOS = "movie/{id}/videos"
        const val BASE_URL_MOVIE = "movie/{id}"
        const val BASE_URL_POPULAR = "movie/popular"
        const val BASE_URL_TOP_RATED = "movie/top_rated"

        const val PARAMS_API_KEY = "api_key"
        const val PARAMS_LANGUAGE = "language"
        const val PARAMS_GENRE = "with_genres"
        const val PARAMS_SORT = "sort_by"
        const val PARAMS_PAGE = "page"
        const val PARAMS_QUERY = "query"

    }
}