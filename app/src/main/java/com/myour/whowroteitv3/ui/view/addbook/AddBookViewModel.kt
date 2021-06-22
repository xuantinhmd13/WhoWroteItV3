package com.myour.whowroteitv3.ui.view.addbook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myour.whowroteitv3.R
import com.myour.whowroteitv3.data.model.local.GBookEntity
import com.myour.whowroteitv3.data.repository.GBookRepository
import com.myour.whowroteitv3.util.ModelMapping
import com.myour.whowroteitv3.util.Response
import com.myour.whowroteitv3.util.SystemUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val app: Application,
    private val mRepository: GBookRepository,
) : AndroidViewModel(app) {

    val getOneBookBySearchAndAddLiveData = MutableLiveData<Response<GBookEntity>>()

    fun getOneBookBySearchAndAdd(queryString: String) =
        viewModelScope.launch(Dispatchers.IO) {
            if (!SystemUtils.hasInternetConnection(app)) {
                getOneBookBySearchAndAddLiveData
                    .postValue(Response.Error(app.getString(R.string.message_no_internet)))
            } else {
                getOneBookBySearchAndAddLiveData.postValue(Response.Loading())
                val response = mRepository.getOneBookBySearch(queryString)
                if (response.isSuccessful) {
                    val gBook = response.body()?.items?.get(0)
                    if (gBook == null) {
                        getOneBookBySearchAndAddLiveData.postValue(Response.Success(null))
                    } else {
                        val gBookEntity = ModelMapping.mappingResponseToEntity(gBook)
                        mRepository.insertBook(gBookEntity)
                        getOneBookBySearchAndAddLiveData.postValue(Response.Success(gBookEntity))
                    }
                } else getOneBookBySearchAndAddLiveData.postValue(Response.Error(response.message()))
            }
        }
}