package kmp.template.app

import android.app.Application
import kmp.template.app.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() = initKoin {
        androidContext(applicationContext)
        androidLogger()
    }
}
