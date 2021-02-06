package com.structure.gallery.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.structure.gallery.data.local.dao.SnapsDao
import com.structure.gallery.model.Snap

@Database(
    entities = [Snap::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class GallerySnapsDatabase : RoomDatabase() {


    abstract fun getPostsDao(): SnapsDao

    companion object {
        const val DB_NAME = "gallery_database"

        @Volatile
        private var INSTANCE: GallerySnapsDatabase? = null

        fun getInstance(context: Context): GallerySnapsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        GallerySnapsDatabase::class.java,
                        DB_NAME
                    )
                    .addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }
}