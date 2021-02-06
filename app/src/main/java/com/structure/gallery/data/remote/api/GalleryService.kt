package com.structure.gallery.data.remote.api

import com.structure.gallery.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryService {

    @GET("?key=20147236-c054cfdc9237661476c548d71")
    suspend fun getPosts(@Query("category") type : String?, @Query("q") search : String?): Response<ResponseData>

    companion object {
        const val IMAGES_API_URL = "https://pixabay.com/api/"
    }
}