package com.andyshon.flickrapi.di.module

import com.andyshon.flickrapi.di.scope.PresentationScope
import com.andyshon.flickrapi.ui.base.DialogProvider
import com.andyshon.flickrapi.ui.base.presentation.PresentationComponentProvider
import dagger.Module
import dagger.Provides

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

}