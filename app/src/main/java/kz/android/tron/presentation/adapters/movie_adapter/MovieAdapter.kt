package kz.android.tron.presentation.adapters.movie_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import kz.android.tron.databinding.MovieItemRowBinding
import kz.android.tron.domain.model.Movie
import javax.inject.Inject



class MovieAdapter @Inject constructor() : PagingDataAdapter<Movie, MovieHolder>(MovieDiffUtil) {

    var onMovieClickListener: ((Movie) -> Unit)? = null

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

}
