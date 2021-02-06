package com.structure.gallery.di.module

import android.app.Application
import com.structure.gallery.data.local.GallerySnapsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GalleryDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = GallerySnapsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: GallerySnapsDatabase) = database.getPostsDao()
}