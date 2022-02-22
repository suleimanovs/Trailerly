package com.android.tron.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.tron.R
import com.osmanboy.mymovie.api.TOP_RATED

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<MainMovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

}