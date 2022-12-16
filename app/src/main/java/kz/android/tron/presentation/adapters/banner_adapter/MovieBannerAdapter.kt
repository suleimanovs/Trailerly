package kz.android.tron.presentation.adapters.banner_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import kz.android.tron.databinding.BannersMovieLayoutBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.adapters.movie_adapter.MovieDiffUtil
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder
import kz.android.tron.presentation.util.toRound
import javax.inject.Inject


class MovieBannerAdapter @Inject constructor() : PagingDataAdapter<Movie, BannerViewHolder>(MovieDiffUtil) {

    var onMovieClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BannerViewHolder(BannersMovieLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
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