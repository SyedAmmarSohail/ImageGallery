package com.structure.gallery.di.module

import androidx.lifecycle.ViewModelProvider
import com.structure.gallery.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}