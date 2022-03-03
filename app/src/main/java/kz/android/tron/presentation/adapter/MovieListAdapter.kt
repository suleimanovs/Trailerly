package kz.android.tron.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.android.tron.databinding.MovieItemRowBinding
import kz.android.tron.domain.model.Movie
import javax.inject.Inject

typealias OnReachEndListener = () -> Unit
typealias OnPosterClickListener = (Movie) -> Unit

class MovieAdapter @Inject constructor() : ListAdapter<Movie, MovieAdapter.ItemViewHolder>(MovieItemDiffUtil()) {

    var onReachEndListener: OnReachEndListener? = null
    var onPosterClickListener: OnPosterClickListener? = null

    class ItemViewHolder(val binding: MovieItemRowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(MovieItemRowBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.movie = getItem(position)
        if (position >= (currentList.size - 6)) onReachEndListener?.invoke()
        holder.binding.root.setOnClickListener {
            onPosterClickListener?.invoke(getItem(position))
        }

    }

}



