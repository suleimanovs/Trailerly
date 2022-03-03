package kz.android.tron.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import kz.android.tron.domain.model.Movie

/**
 * Created by osmanboy on 2/22/2022.
 */
class MovieItemDiffUtil : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}