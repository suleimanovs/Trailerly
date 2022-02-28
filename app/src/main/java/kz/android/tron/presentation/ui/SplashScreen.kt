package kz.android.tron.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.android.tron.presentation.adapter.TronSharedPreferences
import kz.android.tron.presentation.ui.login.LoginActivity


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        TronSharedPreferences.initial(this)
        super.onCreate(savedInstanceState)

        if (TronSharedPreferences.getUser().isNullOrEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}




