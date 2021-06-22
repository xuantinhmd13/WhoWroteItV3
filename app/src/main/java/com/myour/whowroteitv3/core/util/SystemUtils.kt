package com.myour.whowroteitv3.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.inputmethod.InputMethodManager

object SystemUtils {

    fun hasInternetConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo? = null

        connMgr?.let { networkInfo = connMgr.activeNetworkInfo }
        return networkInfo != null && networkInfo!!.isConnected
    }

    fun hideKeyboard(context: Context, view: View) {
        val inputManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputManager?.hideSoftInputFromWindow(view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS)
    }
}