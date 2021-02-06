package com.structure.gallery.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.structure.gallery.data.remote.api.GalleryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class GalleryApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): GalleryService = Retrofit.Builder()
        .baseUrl(GalleryService.IMAGES_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(GalleryService::class.java)

}
