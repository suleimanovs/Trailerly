package kz.android.tron.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kz.android.tron.databinding.BannersMovieLayoutBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder
import kz.android.tron.presentation.util.toRound
import javax.inject.Inject


class MovieBannerAdapter @Inject constructor() : PagingDataAdapter<Movie, MovieBannerAdapter.BannerViewHolder>(MovieAdapter.MovieDiffUtil) {

    var onMovieClickListener: OnMovieClickListener? = null

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

    class BannerViewHolder(val binding: BannersMovieLayoutBinding) : ViewHolder(binding.root)

}