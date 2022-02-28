package kz.android.tron.presentation.adapter

import kz.android.tron.R

/**
 * Created by osmanboy on 2/19/2022.
 */
data class Genre(val name: String, val icon: Int)

object Genres {

    val genres = mapOf(
        12 to Genre("приключения", R.drawable.ic_adventure_genre),
        14 to Genre("фэнтези", R.drawable.ic_fentezy_genre),
        16 to Genre("мультфильм", R.drawable.ic_anime_genre),
        18 to Genre("драма", R.drawable.ic_drama_genre),
        27 to Genre("ужасы", R.drawable.ic_horror_genre),
        28 to Genre("боевик", R.drawable.ic_adventure_genre),
        35 to Genre("комедия", R.drawable.ic_comedy_genre),
        36 to Genre("история", R.drawable.ic_history_genre),
        37 to Genre("вестерн", R.drawable.ic_western_genre),
        53 to Genre("триллер", R.drawable.ic_thriller__genre),
        80 to Genre("криминал", R.drawable.ic_criminal_genre),
        99 to Genre("документальный", R.drawable.ic_music_genre),
        878 to Genre("фантастика", R.drawable.ic_fantasy_genre),
        1075 to Genre("семейный", R.drawable.ic_family_genre),
        9648 to Genre("детектив", R.drawable.ic_detective_genre),
        10402 to Genre("музыка", R.drawable.ic_music_genre),
        10749 to Genre("мелодрама", R.drawable.ic_melodrama_genre),
        10752 to Genre("военный", R.drawable.ic_hat_military_genre),
        10770 to Genre("телевизионный фильм", R.drawable.ic_tv_show_genre)
    )


    fun getGenreNameById(id: Int): String {
        return genres[id]?.name ?: throw RuntimeException("Genre by id: $id not found!")
    }


    fun getGenresName(list: List<Int>?): StringBuilder {
        val result = StringBuilder()
        list?.map { id -> genres[id]?.let { result.append(it.name + ", ") } }
        return result
    }

}


