package com.myour.whowroteitv3.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myour.whowroteitv3.data.datasource.local.dao.IGBookDAO
import com.myour.whowroteitv3.data.datasource.local.entity.GBookEntity

@Database(entities = [GBookEntity::class], version = 1, exportSchema = false)
abstract class GBookDatabase : RoomDatabase() {
    abstract val GBookDAO: IGBookDAO
}