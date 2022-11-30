package kz.android.tron.presentation.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.vaibhavlakhera.circularprogressview.CircularProgressView
import kz.android.tron.R
import kz.android.tron.presentation.util.Genres
import kz.android.tron.presentation.util.ShimmerDrawablePlaceHolder
import kz.android.tron.presentation.util.toRound


// Movie List Adapter
@BindingAdapter("setPosterImage")
fun setPosterImage(imageView: ShapeableImageView, posterUrl: String) {
    imageView.toRound()
    Glide.with(imageView.context).load(posterUrl).centerCrop()
        .placeholder(ShimmerDrawablePlaceHolder(imageView.context))
        .into(imageView)
}

@BindingAdapter("setVoteProgress")
fun setVoteProgress(circularProgress: CircularProgressView, percent: Int) {
    val (@ColorRes track, @ColorRes bar) = when (percent) {
        in VoteColor.LOW.range -> R.color.voteLowTrackColor to R.color.voteLowBarColor
        in VoteColor.MIDDLE.range -> R.color.voteMiddleTrackColor to R.color.voteMiddleBarColor
        in VoteColor.HIGH.range -> R.color.voteHighTrackColor to R.color.voteHighBarColor
        else -> throw RuntimeException("Color by person: $percent not found")
    }
    circularProgress.setTotalColorRes(track)
    circularProgress.setProgressColorRes(bar)
}

@BindingAdapter("setVoteProgressValue")
fun setVoteProgressValue(circularProgress: CircularProgressView, percent: Int) {
    circularProgress.setProgress(percent)
}


enum class VoteColor(val range: IntRange) {
    LOW(0..39),
    MIDDLE(40..69),
    HIGH(70..100);
}


// Genre Adapter
@BindingAdapter("setGenreIcon")
fun setGenreIcon(imageView: ImageView, icon: Int) {
    imageView.setImageResource(icon)
}

//Movie Detail
@BindingAdapter("setGenresValue")
fun setGenresValue(textView: TextView, genresId: List<Int>) {
    textView.text = Genres.getGenresName(genresId).toString()
}



