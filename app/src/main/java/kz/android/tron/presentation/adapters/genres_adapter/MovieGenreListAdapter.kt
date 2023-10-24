package kz.android.tron.presentation.adapters.genres_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import kz.android.tron.databinding.GenreItemBinding
import kz.android.tron.presentation.adapters.banner_adapter.BaseViewHolder
import kz.android.tron.presentation.adapters.banner_adapter.toBinding
import kz.android.tron.presentation.util.Genres


class MoviesGenreAdapter @Inject constructor() : RecyclerView.Adapter<BaseViewHolder<GenreItemBinding>>() {

    var genreClickListener: ((Int) -> Unit)? = null
    private val genreList = Genres.genres.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<GenreItemBinding>(parent.toBinding())

    override fun onBindViewHolder(holder: BaseViewHolder<GenreItemBinding>, position: Int) {
        val genre = genreList[position]
        holder.binding.genre = genre.second
        holder.binding.root.setOnClickListener {
            genreClickListener?.invoke(genre.first)
        }
    }

    override fun getItemCount(): Int = genreList.size
}