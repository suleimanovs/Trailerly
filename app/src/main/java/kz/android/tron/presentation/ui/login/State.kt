package kz.android.tron.presentation.ui.login

import androidx.annotation.StringRes

sealed class State {
    class Error(@StringRes val resId: Int) : State()
    object Success : State()
    object Finished : State()
}