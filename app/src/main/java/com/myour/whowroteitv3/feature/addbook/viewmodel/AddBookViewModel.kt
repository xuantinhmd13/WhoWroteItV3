package com.myour.whowroteitv3.feature.addbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.myour.whowroteitv3.R
import com.myour.whowroteitv3.core.api.Response
import com.myour.whowroteitv3.core.util.ModelMapping
import com.myour.whowroteitv3.core.util.SystemUtils
import com.myour.whowroteitv3.data.repository.GBookRepository
import com.myour.whowroteitv3.data.repository.model.GBookModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val app: Application,
    private val mRepository: GBookRepository,
) : AndroidViewModel(app) {

    val getOneBookBySearchAndAddLiveData = MutableLiveData<Response<GBookModel>>()

    fun getOneBookBySearchAndAdd(queryString: String) =
        viewModelScope.launch(Dispatchers.IO) {
            if (!SystemUtils.hasInternetConnection(app)) {
                getOneBookBySearchAndAddLiveData
                    .postValue(Response.Error(app.getString(R.string.message_no_internet)))
            } else {
                getOneBookBySearchAndAddLiveData.postValue(Response.Loading())
                val response = mRepository.getOneBookBySearch(queryString)
                if (response.isSuccessful) {
                    val gBookResponse = response.body()?.items?.get(0)
                    if (gBookResponse == null) {
                        getOneBookBySearchAndAddLiveData.postValue(Response.Success(null))
                    } else {
                        val gBookEntity = ModelMapping.mappingResponseToEntity(gBookResponse)
                        val gBookModel = ModelMapping.mappingResponseToRepoModel(gBookResponse)
                        mRepository.insertBook(gBookEntity)
                        getOneBookBySearchAndAddLiveData.postValue(Response.Success(gBookModel))
                    }
                } else getOneBookBySearchAndAddLiveData.postValue(Response.Error(response.message()))
            }
        }
}