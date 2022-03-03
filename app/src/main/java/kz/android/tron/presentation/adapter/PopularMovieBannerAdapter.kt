package kz.android.tron.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kz.android.tron.databinding.BannersMovieLayoutBinding
import kz.android.tron.domain.model.Movie
import javax.inject.Inject

class PopularMovieBannerAdapter @Inject constructor() :
    ListAdapter<Movie, PopularMovieBannerAdapter.ItemViewHolder>(
        MovieItemDiffUtil()
    ) {
    var onPosterClickListener: OnPosterClickListener? = null

    class ItemViewHolder(val binding: BannersMovieLayoutBinding) : ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(BannersMovieLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val context = holder.binding.root.context

        holder.binding.bannerImage.apply {
            setOnClickListener { onPosterClickListener?.invoke(getItem(position)) }

            toRound()
            Glide.with(context).load(getItem(position).backdrop_path).centerCrop()
                .placeholder(ShimmerDrawablePlaceHolder(context))
                .into(this)
        }
    }
}