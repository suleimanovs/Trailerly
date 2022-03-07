package kz.android.tron.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kz.android.tron.databinding.BannersMovieLayoutBinding
import kz.android.tron.domain.model.Movie
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder
import kz.android.tron.presentation.util.toRound
import javax.inject.Inject

class ItemVH(val binding: BannersMovieLayoutBinding) : ViewHolder(binding.root)

class MovieBannerAdapter @Inject constructor() : ListAdapter<Movie,ItemVH>(MovieItemDiffUtil()) {
    var onPosterClickListener: OnPosterClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        val inflater = LayoutInflater.from(parent.context)
        return ItemVH(BannersMovieLayoutBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        val context = holder.binding.root.context

        holder.binding.bannerImage.apply {
            setOnClickListener { onPosterClickListener?.invoke(getItem(position)) }

            this.toRound()
            Glide.with(context).load(getItem(position).backdrop_path).centerCrop()
                .placeholder(ShimmerDrawablePlaceHolder(context))
                .into(this)
        }
    }
}