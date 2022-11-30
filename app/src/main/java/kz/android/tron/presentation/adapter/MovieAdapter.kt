package kz.android.tron.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.android.tron.databinding.MovieItemRowBinding
import kz.android.tron.domain.model.Movie
import javax.inject.Inject


typealias OnMovieClickListener = (Movie) -> Unit

class MovieAdapter @Inject constructor() : PagingDataAdapter<Movie, MovieAdapter.MovieHolder>(MovieDiffUtil) {

    var onMovieClickListener: OnMovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieHolder(MovieItemRowBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.apply {
            holder.binding.movie = this
            holder.binding.root.setOnClickListener { onMovieClickListener?.invoke(this) }
        }
    }

    class MovieHolder(val binding: MovieItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    object MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}
