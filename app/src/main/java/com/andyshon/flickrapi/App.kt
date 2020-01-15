package com.andyshon.flickrapi

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import timber.log.Timber
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    companion object {
        operator fun get(context: Context): DaggerComponentProvider {
            return (context.applicationContext as App).componentProvider
        }
    }

    private val componentProvider: DaggerComponentProvider = DaggerComponentProvider(this)


    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        initRealm()

    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}