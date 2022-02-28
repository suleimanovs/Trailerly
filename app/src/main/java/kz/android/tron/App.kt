package kz.android.tron

import android.app.Application
import kz.android.tron.di.AppComponent
import kz.android.tron.di.DaggerAppComponent

/**
 * Created by osmanboy on 2/26/2022.
 */
class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}