package com.myour.whowroteitv3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myour.whowroteitv3.data.model.local.GBookEntity

@Database(entities = [GBookEntity::class], version = 1, exportSchema = false)
abstract class GBookDatabase : RoomDatabase() {
    abstract val GBookDAO: IGBookDAO
}