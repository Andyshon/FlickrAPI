package com.andyshon.flickrapi.di.module

import android.content.Context
import com.andyshon.flickrapi.App
import com.andyshon.flickrapi.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideApplication(): App {
        return app
    }

}