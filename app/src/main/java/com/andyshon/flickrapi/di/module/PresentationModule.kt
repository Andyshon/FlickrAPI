package com.andyshon.flickrapi.di.module

import com.andyshon.flickrapi.data.repository.local.RealmService
import com.andyshon.flickrapi.di.scope.PresentationScope
import com.andyshon.flickrapi.ui.base.DialogProvider
import com.andyshon.flickrapi.ui.base.presentation.PresentationComponentProvider
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class PresentationModule(private val uiProvider: PresentationComponentProvider) {

    @Provides
    @PresentationScope
    fun provideFragmentManager() = uiProvider.provideSupportFragmentManager()

    @Provides
    @PresentationScope
    fun provideDialog(): DialogProvider = DialogProvider()

    @Provides
    @PresentationScope
    fun provideCompositeDisposable() = uiProvider.getDestroyDisposable()

    @Provides
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    @Provides
    fun provideRealmService(realm: Realm): RealmService {
        return RealmService(realm)
    }

}