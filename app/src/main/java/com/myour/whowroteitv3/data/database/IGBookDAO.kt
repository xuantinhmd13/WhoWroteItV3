package com.myour.whowroteitv3.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.myour.whowroteitv3.data.model.local.GBookEntity

@Dao
interface IGBookDAO {
    @Query("SELECT * FROM gbook ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<GBookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: GBookEntity)

    @Delete
    suspend fun deleteBook(book: GBookEntity)

    @Query("DELETE FROM gbook")
    suspend fun deleteAllBooks()
}