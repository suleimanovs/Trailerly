package kz.android.tron.presentation.util

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kz.android.tron.R

fun Fragment.showSnackbar(@StringRes stringRes: Int) = view?.let {
    Snackbar.make(requireContext(), it, getString(stringRes), Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showSnackbar(message: String) = view?.let {
    Snackbar.make(requireContext(), it, message, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showErrorSnackbar(message: String) = view?.let {
    Snackbar.make(requireContext(), it, message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(resources.getColor(R.color.error_color, null))
        .setTextColor(resources.getColor(R.color.white, null))
        .show()
}

fun Fragment.showSuccessSnackbar(message: String) = view?.let {
    Snackbar.make(requireContext(), it, message, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(resources.getColor(R.color.success_color, null))
        .setTextColor(resources.getColor(R.color.white, null))
        .show()
}