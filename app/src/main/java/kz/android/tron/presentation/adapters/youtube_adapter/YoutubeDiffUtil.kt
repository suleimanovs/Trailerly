package kz.android.tron.presentation.adapters.youtube_adapter

import androidx.recyclerview.widget.DiffUtil
import kz.android.tron.domain.model.Trailer

object YoutubeDiffUtil : DiffUtil.ItemCallback<Trailer>() {

    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}