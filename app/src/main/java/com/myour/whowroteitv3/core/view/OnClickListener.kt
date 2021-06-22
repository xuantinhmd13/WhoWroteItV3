package com.myour.whowroteitv3.core.view

import android.view.View

interface ItemClickListener {

    fun onItemClick(v: View?, position: Int)
}

interface ItemLongClickListener {

    fun onItemLongClick(v: View?, position: Int): Boolean
}