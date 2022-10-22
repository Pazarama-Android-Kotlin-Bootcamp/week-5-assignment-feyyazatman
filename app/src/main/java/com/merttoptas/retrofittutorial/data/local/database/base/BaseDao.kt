package com.merttoptas.retrofittutorial.data.local.database.base

import androidx.room.*

/**
 * Created by merttoptas on 16.10.2022.
 */

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<T>)

    @Update
    fun update(data: T)

    @Delete
    fun delete(data: T)
}