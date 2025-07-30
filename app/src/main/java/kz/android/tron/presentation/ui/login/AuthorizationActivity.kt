package kz.android.tron.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dev.androidbroadcast.vbpd.viewBinding
import kz.android.tron.R
import kz.android.tron.databinding.ActivityAuthorizationBinding
import kz.android.tron.presentation.ui.MainActivity

class AuthorizationActivity : AppCompatActivity(R.layout.activity_authorization), OnStartActivity {

    private val binding by viewBinding(ActivityAuthorizationBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun onStartActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

interface OnStartActivity {
    fun onStartActivity()
}