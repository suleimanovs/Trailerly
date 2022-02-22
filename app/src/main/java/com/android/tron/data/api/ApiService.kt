package com.osmanboy.mymovie.api

import com.android.tron.data.api.pojo.Page
import com.android.tron.data.api.pojo.ReviewContainer
import com.android.tron.data.api.pojo.TrailerContainer
import com.android.tron.domain.pojo.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/3/"
private const val BASE_URL_SEARCH = "search/movie"
private const val BASE_URL_MOVIES = "discover/movie"
private const val BASE_URL_REVIEWS = "movie/{id}/reviews"
private const val BASE_URL_VIDEOS = "movie/{id}/videos"
private const val BASE_URL_MOVIE = "/movie/{id}"
private const val BASE_URL_POPULAR = "movie/popular"
private const val BASE_URL_TOP_RATED = "movie/top_rated"


private const val PARAMS_API_KEY = "api_key"
private const val PARAMS_LANGUAGE = "language"
private const val PARAMS_SORT = "sort_by"
private const val PARAMS_PAGE = "page"
private const val PARAMS_MIN_VOTE_COUNT = "vote_count.gte"

private const val PARAMS_QUERY = "query"


private const val API_KEY = "91980027d393239a4d9b9b8da5a39c14"
private const val LANGUAGE_VALUE = "ru-RU"

private const val SORT_BY_POPULARITY = "popularity.desc"
private const val SORT_BY_TOP_RATED = "vote_average.desc"
private const val MIN_VOTE_COUNT = "1000"
const val POPULARITY = 0
const val TOP_RATED = 1

interface ApiService {




    @GET(BASE_URL_MOVIES)
    suspend fun getAllMovies(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_SORT) methodOfSort: String = SORT_BY_TOP_RATED,
        @Query(PARAMS_MIN_VOTE_COUNT) minVoteCount: String = MIN_VOTE_COUNT,
        @Query(PARAMS_PAGE) page: Int
    ): Page

    @GET(BASE_URL_VIDEOS)
    suspend fun getMovieTrailersById(
        @Path("id") id: Int,
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
    ): TrailerContainer

    @GET(BASE_URL_REVIEWS)
    suspend fun getMovieReviewsById(
        @Path("id") id: Int,
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
    ): ReviewContainer

    @GET(BASE_URL_SEARCH)
    suspend fun searchMovie(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_QUERY) query: String
    ): Page


    @GET(BASE_URL_POPULAR)
    suspend fun getPopularMovies(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_PAGE) page: Int
    ): Page


    @GET(BASE_URL_POPULAR)
    suspend fun getTopRatedMovies(
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
        @Query(PARAMS_PAGE) page: Int
    ): Page


    @GET(BASE_URL_MOVIE)
    suspend fun getMovieById(
        @Path("id") id: Int,
        @Query(PARAMS_API_KEY) apiKey: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = LANGUAGE_VALUE,
    ): Movie

}