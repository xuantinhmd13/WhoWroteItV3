package com.myour.whowroteitv3.data.repository

import com.myour.whowroteitv3.data.api.IGBookAPI
import com.myour.whowroteitv3.data.database.IGBookDAO
import com.myour.whowroteitv3.data.model.local.GBookEntity
import javax.inject.Inject

class GBookRepository @Inject constructor(
    private val mGBookDAO: IGBookDAO,
    private val mGBookAPI: IGBookAPI,
) {

    fun getAllBooks() = mGBookDAO.getAllBooks()

    suspend fun insertBook(book: GBookEntity) {
        mGBookDAO.insertBook(book)
    }

    suspend fun deleteBook(book: GBookEntity) {
        mGBookDAO.deleteBook(book)
    }

    suspend fun deleteAllBooks() {
        mGBookDAO.deleteAllBooks()
    }

    suspend fun getOneBookBySearch(queryString: String) =
        mGBookAPI.getOneBookBySearch(queryString, 1, "books", "epub")
}