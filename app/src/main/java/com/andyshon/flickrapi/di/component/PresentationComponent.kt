package com.andyshon.flickrapi.di.component

import com.andyshon.flickrapi.di.module.PresentationModule
import com.andyshon.flickrapi.di.scope.PresentationScope
import com.andyshon.flickrapi.ui.search.SearchActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    @Subcomponent.Builder
    interface Builder {
        fun presentationModule(module: PresentationModule): Builder
        fun build(): PresentationComponent
    }

    fun inject(activity: SearchActivity)
}