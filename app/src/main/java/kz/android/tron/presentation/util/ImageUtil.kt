package kz.android.tron.presentation.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import kz.android.tron.R

/**
 * Created by osmanboy on 2/24/2022.
 */
object DpToPx {
        operator fun invoke(context: Context, dp: Int): Int {
            return (dp * context.resources.displayMetrics.density).toInt()
        }
}

fun ShapeableImageView.toRound() {
    this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
        .setAllCorners(CornerFamily.ROUNDED, DpToPx(context, 8).toFloat()).build()
}


object ShimmerDrawablePlaceHolder {
        operator fun invoke(context: Context): ShimmerDrawable {
            val shimmer = Shimmer.ColorHighlightBuilder()
                .setBaseColor(ContextCompat.getColor(context, R.color.teal_200)).setBaseAlpha(0.0f)
                .setHighlightAlpha(0.7f)
                .setHighlightColor(ContextCompat.getColor(context, R.color.grey))
                .setDuration(1800).setDirection(Shimmer.Direction.LEFT_TO_RIGHT).setAutoStart(true)
                .build()
            return ShimmerDrawable().apply { setShimmer(shimmer) }
        }
}

