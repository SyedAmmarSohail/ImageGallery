package com.structure.gallery.di.component

import android.app.Application
import com.structure.gallery.GalleryApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

import com.structure.gallery.di.builder.ActivityBuilder
import com.structure.gallery.di.module.GalleryApiModule
import com.structure.gallery.di.module.GalleryDatabaseModule
import com.structure.gallery.di.module.ViewModelFactoryModule
import com.structure.gallery.di.module.ViewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        GalleryDatabaseModule::class,
        GalleryApiModule::class,
        ActivityBuilder::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<GalleryApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: GalleryApp)
}