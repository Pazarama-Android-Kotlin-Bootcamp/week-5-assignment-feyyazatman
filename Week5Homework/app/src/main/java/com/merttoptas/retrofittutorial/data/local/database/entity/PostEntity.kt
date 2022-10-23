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
    @PrimaryKey()
    @ColumnInfo(name = "postId") val postId: Int?,
    @ColumnInfo(name = "postTitle") val postTitle: String?,
    @ColumnInfo(name = "postBody") val postBody: String?,
)
