package com.myour.whowroteitv3.util

sealed class Response<T>(
    val data: T? = null,
) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T?) : Response<T>(data)
    class Error<T>(val message: String) : Response<T>()
}
