package kz.android.tron.presentation.adapters.banner_adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import kz.android.tron.databinding.BannersMovieLayoutBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.adapters.base.BaseViewHolder
import kz.android.tron.presentation.adapters.base.toBinding
import kz.android.tron.presentation.adapters.movie_adapter.MovieAdapter
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder
import javax.inject.Inject

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
                    .into(banner)
        }
    }
}

