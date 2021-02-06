package com.structure.gallery.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.structure.gallery.model.Snap
import kotlinx.coroutines.flow.Flow

@Dao
interface SnapsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Snap>)

    @Query("DELETE FROM ${Snap.TABLE_NAME}")
    fun deleteAllPosts()

    @Query("SELECT * FROM ${Snap.TABLE_NAME} WHERE ID = :postId")
    fun getPostById(postId: Int): Flow<Snap>

    @Query("SELECT * FROM ${Snap.TABLE_NAME}")
    fun getAllPosts(): Flow<List<Snap>>
}