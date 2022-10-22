package com.merttoptas.retrofittutorial.data.local.database.converter

import androidx.room.TypeConverter

/**
 * Created by merttoptas on 16.10.2022.
 */
class DaoConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.split(",").map { it }
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString?.joinToString(separator = ",") ?: ""
    }
}