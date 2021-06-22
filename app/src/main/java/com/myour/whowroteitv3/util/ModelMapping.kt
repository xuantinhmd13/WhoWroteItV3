package com.myour.whowroteitv3.util

import com.myour.whowroteitv3.data.model.local.GBookEntity
import com.myour.whowroteitv3.data.model.Item

object ModelMapping {

    fun mappingResponseToEntity(item: Item): GBookEntity {
        return GBookEntity(
            id = item.id,
            title = item.volumeInfo?.title ?: "",
            authors = item.volumeInfo?.authors?.toString() ?: "",
            publishedDate = item.volumeInfo?.publishedDate ?: "",
            smallThumbnail = item.volumeInfo?.imageLinks?.smallThumbnail ?: ""
        )
    }
}