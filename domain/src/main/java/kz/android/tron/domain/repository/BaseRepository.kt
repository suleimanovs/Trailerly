package kz.android.tron.domain.repository

import kz.android.tron.domain.mapper.BaseMapper

interface BaseRepository {

    fun <R> handleSuccess(result: R): Result<R> = Result.success(result)
    fun <R> handleError(throwable: Throwable): Result<R> = Result.failure(throwable)
}

suspend fun <T> BaseRepository.apiCall(result: suspend () -> T): Result<T> =
    runCatching { result.invoke() }.fold(::handleSuccess, ::handleError)

suspend fun <FROM, TO> BaseRepository.mappedApiCall(mapper: BaseMapper<FROM, TO>, call: suspend () -> FROM): Result<TO> =
    runCatching { call.invoke().let(mapper::map) }.fold(::handleSuccess, ::handleError)

suspend fun <FROM, TO> BaseRepository.mappedApiCallList(mapper: BaseMapper<FROM, TO>, call: suspend () -> List<FROM>): Result<List<TO>> =
    runCatching { call().map(mapper::map) }.fold(::handleSuccess, ::handleError)
