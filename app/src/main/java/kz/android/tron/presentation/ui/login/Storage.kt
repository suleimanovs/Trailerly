package kz.android.tron.presentation.ui.login

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by osmanboy on 3/4/2022.
 */
object Storage {

    private const val USER = "user_param"
    private lateinit var shareds: SharedPreferences

    fun initial(context: Context) {
        shareds = context.getSharedPreferences("user", 0)
    }

    fun putUser(user: String) = shareds.edit().putString(USER, user).apply()

    fun getUser() = shareds.getString(USER, null)
}