package kz.android.tron.presentation.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.android.tron.presentation.ui.MainActivity
import kz.android.tron.presentation.ui.login.LoginActivity
import kz.android.tron.presentation.ui.login.Storage


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Storage.initial(this)
        super.onCreate(savedInstanceState)

        val activity= Storage.getUser()?.let { MainActivity::class.java }?:LoginActivity::class.java
        startActivity(Intent(this, activity))
        finish()
    }
}




