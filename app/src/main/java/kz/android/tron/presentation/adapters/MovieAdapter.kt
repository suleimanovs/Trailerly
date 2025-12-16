package kz.android.tron.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kz.android.tron.databinding.BannersMovieLayoutBinding
import kz.android.tron.databinding.MovieItemGridBinding
import kz.android.tron.databinding.MovieItemRowBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder.invoke

class MovieAdapter(val layoutType: LayoutType) : PagingDataAdapter<Movie, MovieAdapter.MovieBaseViewHolder<ViewDataBinding>>(MovieDiffUtil) {

    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBaseViewHolder<ViewDataBinding> {
        val layoutType = LayoutType.entries[viewType]
        val binding = layoutType.binder.invoke(LayoutInflater.from(parent.context), parent, false)
        return layoutType.holder.constructors[0].newInstance(binding) as MovieBaseViewHolder<ViewDataBinding>
    }

    override fun onBindViewHolder(holder: MovieBaseViewHolder<ViewDataBinding>, position: Int) {
        val movie = getItem(position) ?: return
        holder.binding.root.setOnClickListener { onMovieClickListener?.invoke(movie) }
        holder.onBind(movie)
    }

    sealed class MovieBaseViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root) {

        abstract fun onBind(movie: Movie)

        class MovieListHolder(binding: MovieItemRowBinding) : MovieBaseViewHolder<MovieItemRowBinding>(binding) {
            override fun onBind(movie: Movie) {
                binding.movie = movie
                itemView.alpha = 0f
                itemView.animate().alpha(1f).setDuration(300).setStartDelay(50L).start()
            }
        }

        class MovieGridHolder(binding: MovieItemGridBinding) : MovieBaseViewHolder<MovieItemGridBinding>(binding) {
            override fun onBind(movie: Movie) {
                binding.movie = movie
                itemView.alpha = 0f
                itemView.animate().alpha(1f).setDuration(300).setStartDelay(50L).start()
            }
        }

        class MovieBannerHolder(binding: BannersMovieLayoutBinding) : MovieBaseViewHolder<BannersMovieLayoutBinding>(binding) {
            override fun onBind(movie: Movie) {
                Glide.with(binding.root.context).load(movie.backdropPath).centerCrop()
                    .placeholder(ShimmerDrawablePlaceHolder(binding.root.context))
                    .into(binding.bannerImage)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return layoutType.ordinal
    }

    enum class LayoutType(val binder: (LayoutInflater, ViewGroup, Boolean) -> ViewDataBinding, val holder: Class<out MovieBaseViewHolder<*>>) {
        GRID(MovieItemGridBinding::inflate, MovieBaseViewHolder.MovieGridHolder::class.java),
        LIST(MovieItemRowBinding::inflate, MovieBaseViewHolder.MovieListHolder::class.java),
        BANNER(BannersMovieLayoutBinding::inflate, MovieBaseViewHolder.MovieBannerHolder::class.java)
    }

    object MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
    }
}