package com.andyshon.flickrapi.di.component

import com.andyshon.flickrapi.di.module.PresentationModule
import com.andyshon.flickrapi.di.scope.PresentationScope
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    @Subcomponent.Builder
    interface Builder {
        fun presentationModule(module: PresentationModule): Builder
        fun build(): PresentationComponent
    }

}