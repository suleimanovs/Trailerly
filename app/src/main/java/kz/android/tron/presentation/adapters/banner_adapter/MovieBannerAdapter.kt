package kz.android.tron.presentation.adapters.banner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import kz.android.tron.databinding.BannersMovieLayoutBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder
import kz.android.tron.presentation.util.toRound
import javax.inject.Inject
import kz.android.tron.presentation.adapters.movie_adapter.MovieAdapter


class BaseViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)


class MovieBannerAdapter @Inject constructor() : PagingDataAdapter<Movie, BaseViewHolder<BannersMovieLayoutBinding>>(MovieAdapter.MovieDiffUtil) {

    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<BannersMovieLayoutBinding>(parent.toBinding())

    override fun onBindViewHolder(holder: BaseViewHolder<BannersMovieLayoutBinding>, position: Int) {
        val context = holder.binding.root.context
        val banner = holder.binding.bannerImage
        val item = getItem(position)

        item?.let { movie ->
            banner.setOnClickListener { onMovieClickListener?.invoke(movie) }
            Glide.with(context).load(movie.backdropPath).centerCrop()
                    .placeholder(ShimmerDrawablePlaceHolder(context))
                    .into(banner.toRound())
        }
    }
}

inline fun <reified V : ViewBinding> ViewGroup.toBinding(): V {
    val inflateMethod = V::class.java.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return inflateMethod.invoke(null, LayoutInflater.from(context), this, false) as V
}