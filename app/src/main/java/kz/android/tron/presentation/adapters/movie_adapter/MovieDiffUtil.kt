package kz.android.tron.presentation.adapters.movie_adapter

import androidx.recyclerview.widget.DiffUtil
import kz.android.tron.domain.model.Movie

object MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}