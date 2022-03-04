package kz.android.tron.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.android.tron.databinding.GenreItemBinding
import kz.android.tron.presentation.adapter.repository.Genres
import javax.inject.Inject

/**
 * Created by Osman Boy on 04.07.2021.
 **/
typealias GenreClickListener = (Int) -> Unit

class MoviesGenreAdapter @Inject constructor() : RecyclerView.Adapter<MoviesGenreAdapter.GenreViewHolder>() {

    var genreClickListener: GenreClickListener? = null
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

    class GenreViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)


}