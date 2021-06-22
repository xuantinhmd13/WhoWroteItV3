package com.myour.whowroteitv3.data.repository

import com.myour.whowroteitv3.data.datasource.remote.service.IGBookService
import com.myour.whowroteitv3.data.datasource.local.dao.IGBookDAO
import com.myour.whowroteitv3.data.datasource.local.entity.GBookEntity
import javax.inject.Inject

class GBookRepository @Inject constructor(
    private val mGBookDAO: IGBookDAO,
    private val mGBookService: IGBookService,
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
        mGBookService.getOneBookBySearch(queryString, 1, "books", "epub")
}