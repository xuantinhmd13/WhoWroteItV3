package com.myour.whowroteitv3.data.api

import com.myour.whowroteitv3.data.model.remote.GBook
import com.myour.whowroteitv3.util.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IGBookAPI {
    @GET("books/v1/volumes")
    suspend fun getOneBookBySearch(
        @Query(Const.QUERY_PARAM) queryStr: String, @Query(Const.MAX_RESULTS) maxResults: Int,
        @Query(Const.PRINT_TYPE) printType: String, @Query(Const.DOWNLOAD) download: String,
    ): Response<GBook>
}