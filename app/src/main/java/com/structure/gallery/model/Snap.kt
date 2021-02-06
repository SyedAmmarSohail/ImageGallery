package com.structure.gallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.structure.gallery.model.Snap.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class Snap(

    @PrimaryKey
    var id: Int? = 0,
    var views: Int? = 0,
    var downloads: Int? = 0,
    var favorites: Int? = 0,
    var title: String? = null,
    var author: String? = null,
    var body: String? = null,
    var imageUrl: String? = null,
    var webformatURL: String? = null
) {
    companion object {
        const val TABLE_NAME = "image_gallery"
    }
}