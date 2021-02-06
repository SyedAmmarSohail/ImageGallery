package com.structure.gallery.data.repository

import androidx.annotation.MainThread
import com.structure.gallery.data.local.dao.SnapsDao
import com.structure.gallery.data.remote.api.GalleryService
import com.structure.gallery.model.ResponseData
import com.structure.gallery.model.Snap
import com.structure.gallery.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class SnapsRepository @Inject constructor(
    private val postsDao: SnapsDao,
    private val galleryService: GalleryService
) {

    fun getAllPosts(search : String?, type : String? ): Flow<State<List<Snap>>> {
        return object : NetworkBoundRepository<List<Snap>, ResponseData>() {

            override suspend fun saveRemoteData(response: ResponseData) {
                postsDao.deleteAllPosts()
                postsDao.insertPosts(response.hits)
            }

            override fun fetchFromLocal(): Flow<List<Snap>> = postsDao.getAllPosts()

            override suspend fun fetchFromRemote(): Response<ResponseData> = galleryService.getPosts(search = search, type = type)

        }.asFlow().flowOn(Dispatchers.IO)
    }

    @MainThread
    fun getPostById(postId: Int): Flow<Snap> = postsDao.getPostById(postId)
}
