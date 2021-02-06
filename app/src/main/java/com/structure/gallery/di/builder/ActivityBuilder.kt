package com.structure.gallery.di.builder

import com.structure.gallery.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
abstract class ActivityBuilder {

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
