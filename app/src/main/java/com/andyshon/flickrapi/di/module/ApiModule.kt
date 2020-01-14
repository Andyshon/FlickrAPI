package com.andyshon.flickrapi.di.module

import com.andyshon.flickrapi.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        @Named("basic")
        fun provideApiClient(@Named("basicClient") client: OkHttpClient, @Named("basic") gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
        }

        @Provides
        @JvmStatic
        @Singleton
        @Named("nulls")
        fun provideNullsApiClient(@Named("basicClient") client: OkHttpClient, @Named("nulls") gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
        }

    }
}
