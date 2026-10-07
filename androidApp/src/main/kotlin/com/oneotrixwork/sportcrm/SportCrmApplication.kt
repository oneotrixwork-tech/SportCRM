package com.oneotrixwork.sportcrm

import android.app.Application
import com.oneotrixwork.sportcrm.di.initKoin
import com.oneotrixwork.sportcrm.di.uiModule
import org.koin.android.ext.koin.androidContext

class SportCrmApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@SportCrmApplication)
            modules(uiModule)
        }
    }
}