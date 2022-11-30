package kz.android.tron.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import kz.android.tron.databinding.YoutubePlayerItemBinding
import kz.android.tron.domain.model.Trailer
import javax.inject.Inject
import kotlin.properties.Delegates


class YoutubePlayerAdapter @Inject constructor() : PagingDataAdapter<Trailer, YoutubePlayerAdapter.YoutubeHolder>(YoutubeDiffUtil) {

    var lifecycle: Lifecycle by Delegates.notNull()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutubeHolder {
        val inflater = LayoutInflater.from(parent.context)
        return YoutubeHolder(YoutubePlayerItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: YoutubeHolder, position: Int) {
        getItem(position)?.apply {
            holder.binding.YouTubePlayer.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadOrCueVideo(lifecycle, key, 0f)

                }
            })
        }
    }

    class YoutubeHolder(val binding: YoutubePlayerItemBinding) : RecyclerView.ViewHolder(binding.root)

    object YoutubeDiffUtil : DiffUtil.ItemCallback<Trailer>() {

        override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
            return oldItem == newItem
        }
    }


}
