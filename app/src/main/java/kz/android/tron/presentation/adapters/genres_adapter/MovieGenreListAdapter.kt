package kz.android.tron.presentation.adapters.genres_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.android.tron.databinding.GenreItemBinding
import kz.android.tron.presentation.util.Genres
import javax.inject.Inject


class MoviesGenreAdapter @Inject constructor() : RecyclerView.Adapter<GenreViewHolder>() {

    var genreClickListener: ((Int) -> Unit)? = null
    private val genreList = Genres.genres.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GenreViewHolder(GenreItemBinding.inflate(inflater, parent, false))


    }
    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genreList[position]
        holder.binding.genre =genre.second
        holder.binding.root.setOnClickListener {
            genreClickListener?.invoke(genre.first)
        }
    }

    override fun getItemCount(): Int = genreList.size


}