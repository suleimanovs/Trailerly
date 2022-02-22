package com.osmanboy.mymovie.api

/**
 * Created by osmanboy on 2/19/2022.
 */

object Genres {
    private val genres = hashMapOf(
        12 to "приключения",
        14 to "фэнтези",
        16 to "мультфильм",
        18 to "драма",
        27 to "ужасы",
        28 to "боевик",
        35 to "комедия",
        36 to "история",
        37 to "вестерн",
        53 to "триллер",
        80 to "криминал",
        99 to "документальный",
        878 to "фантастика",
        1075 to "семейный",
        9648 to "детектив",
        10402 to "музыка",
        10749 to "мелодрама",
        10752 to "военный",
        10770 to "телевизионный фильм"
    )

    fun getGenre(id: Int): String {
        return genres[id] ?: ""
    }

    fun getGenres(list: List<Int>): List<String> {
        val result = mutableListOf<String>()
        for (id in list) {
            genres[id]?.let {
                result.add(it)
            }
        }
        return result
    }
}


