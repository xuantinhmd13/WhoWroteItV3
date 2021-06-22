package com.myour.whowroteitv3.ui.view.showbook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.myour.whowroteitv3.data.model.local.GBookEntity
import com.myour.whowroteitv3.data.repository.GBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowBookViewModel @Inject constructor(
    private val app: Application,
    private val mRepository: GBookRepository,
) : AndroidViewModel(app) {

    fun getAllBooks() = mRepository.getAllBooks()

    fun deleteBook(book: GBookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.deleteBook(book)
        }
    }

    fun deleteAllBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.deleteAllBooks()
        }
    }
}