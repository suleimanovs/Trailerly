package kz.android.tron.presentation.adapters

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import kz.android.tron.databinding.YoutubePlayerItemBinding
import kz.android.tron.domain.model.Trailer
import javax.inject.Inject
import kotlin.properties.Delegates

class YoutubePlayerAdapter @Inject constructor() : PagingDataAdapter<Trailer, BaseViewHolder<YoutubePlayerItemBinding>>(YoutubeDiffUtil) {

    var lifecycle: Lifecycle by Delegates.notNull()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<YoutubePlayerItemBinding>(parent.toBinding())

    override fun onBindViewHolder(holder: BaseViewHolder<YoutubePlayerItemBinding>, position: Int) {
        getItem(position)?.apply {
            holder.binding.YouTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadOrCueVideo(lifecycle, key, 0f)
                }
            })
        }
    }

    object YoutubeDiffUtil : DiffUtil.ItemCallback<Trailer>() {
        override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean = oldItem.key == newItem.key
        override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean = oldItem == newItem
    }
}