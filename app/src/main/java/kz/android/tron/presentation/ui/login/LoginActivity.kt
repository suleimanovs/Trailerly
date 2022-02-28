package kz.android.tron.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.android.tron.R
import kz.android.tron.presentation.ui.MainActivity

class LoginActivity : AppCompatActivity(), LoginFragment.IOStartActivity {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStartActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}