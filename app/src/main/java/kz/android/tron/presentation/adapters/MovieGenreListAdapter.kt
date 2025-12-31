package kz.android.tron.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import kz.android.tron.databinding.GenreItemBinding
import kz.android.tron.presentation.util.Genre

class MoviesGenreAdapter @Inject constructor() : RecyclerView.Adapter<BaseViewHolder<GenreItemBinding>>() {

    var genreClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<GenreItemBinding>(parent.toBinding())

    override fun onBindViewHolder(holder: BaseViewHolder<GenreItemBinding>, position: Int) {
        val genre = Genre.entries[position]
        holder.binding.genre =  genre

        holder.itemView.alpha = 0f
        holder.itemView.translationY = 50f
        holder.itemView.animate().alpha(1f).translationY(0f).setDuration(300).setStartDelay(100L).start()
        holder.binding.root.setOnClickListener {
            genreClickListener?.invoke(genre.id)
        }
    }

    override fun getItemCount(): Int = Genre.entries.size
}