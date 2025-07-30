package kz.android.tron.presentation.ui.login

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.Delegates
import androidx.core.content.edit


object Storage {

    private var sharedPreferences: SharedPreferences by Delegates.notNull()

    /**
     * [initial] Необходимо вызвать в классе приложения и только
     */
    fun initial(context: Context) {
        sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    fun putUser(user: String?) = sharedPreferences.edit { putString(USER, user) }

    fun user() = sharedPreferences.getString(USER, null)

    private const val USER = "user_param"
    const val ANONYMOUS = "anonymous"
}