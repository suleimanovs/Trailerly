package kz.android.tron.presentation.util

import kz.android.tron.R

enum class Genre(val id: Int, val displayName: String, val icon: Int) {

    ADVENTURE(12, "Приключения", R.drawable.ic_adventure_genre),
    FANTASY(14, "Фэнтези", R.drawable.ic_fentezy_genre),
    ANIMATION(16, "Мультфильмы", R.drawable.ic_anime_genre),
    DRAMA(18, "Драма", R.drawable.ic_drama_genre),
    HORROR(27, "Ужасы", R.drawable.ic_horror_genre),
    ACTION(28, "Боевик", R.drawable.ic_adventure_genre),
    COMEDY(35, "Комедия", R.drawable.ic_comedy_genre),
    HISTORY(36, "История", R.drawable.ic_history_genre),
    WESTERN(37, "Вестерн", R.drawable.ic_western_genre),
    THRILLER(53, "Триллер", R.drawable.ic_thriller__genre),
    CRIME(80, "Криминал", R.drawable.ic_criminal_genre),
    DOCUMENTARY(99, "Документальный", R.drawable.ic_music_genre),
    SCI_FI(878, "Фантастика", R.drawable.ic_fantasy_genre),
    FAMILY(1075, "Семейный", R.drawable.ic_family_genre),
    MYSTERY(9648, "Детектив", R.drawable.ic_detective_genre),
    MUSIC(10402, "Музыка", R.drawable.ic_music_genre),
    ROMANCE(10749, "Мелодрама", R.drawable.ic_melodrama_genre),
    WAR(10752, "Военный", R.drawable.ic_hat_military_genre),
    TV_MOVIE(10770, "Телевизионный фильм", R.drawable.ic_tv_show_genre);
}

