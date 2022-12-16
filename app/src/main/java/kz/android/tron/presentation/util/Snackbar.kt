package kz.android.tron.presentation.util

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun Fragment.showSnackbar(@StringRes stringRes: Int) = view?.let {
    Snackbar.make(requireContext(), it, getString(stringRes), Snackbar.LENGTH_SHORT).show()
}