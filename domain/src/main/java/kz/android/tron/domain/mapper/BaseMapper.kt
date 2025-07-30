package kz.android.tron.domain.mapper

fun interface BaseMapper<FROM, TO> {
    fun map(source: FROM): TO
}