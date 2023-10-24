package kz.android.tron.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kz.android.tron.R
import kz.android.tron.presentation.ui.MainActivity
import kz.android.tron.presentation.ui.login.AuthorizationActivity
import kz.android.tron.presentation.ui.login.Storage


class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val activity = Storage.user()?.let { MainActivity::class.java } ?: AuthorizationActivity::class.java
        startActivity(Intent(this, activity))
        finish()
    }

}




