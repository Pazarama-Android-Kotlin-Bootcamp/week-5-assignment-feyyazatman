package com.merttoptas.retrofittutorial.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.merttoptas.retrofittutorial.utils.Constants

/**
 * Created by merttoptas on 16.10.2022.
 */

@Entity(tableName = Constants.TABLE_POST_NAME)
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "postId") val postId: String?,
    @ColumnInfo(name = "postTitle") val postTitle: String?,
    @ColumnInfo(name = "postBody") val postBody: String?,
)
