package kz.android.tron.presentation

import android.app.Application
import kz.android.tron.di.AppComponent
import kz.android.tron.di.DaggerAppComponent
import kz.android.tron.presentation.ui.login.Storage

class TrailerlyApplication : Application() {

    val component: AppComponent by lazy { DaggerAppComponent.builder().build() }

    override fun onCreate() {
        Storage.initial(applicationContext)
        super.onCreate()
    }
}