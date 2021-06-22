package com.myour.whowroteitv3.core.util

import com.myour.whowroteitv3.data.datasource.local.entity.GBookEntity
import com.myour.whowroteitv3.data.datasource.remote.model.Item
import com.myour.whowroteitv3.data.repository.model.GBookModel

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

    fun mappingResponseToRepoModel(item: Item): GBookModel {
        return GBookModel(
            title = item.volumeInfo?.title ?: "",
            authors = item.volumeInfo?.authors?.toString() ?: "",
        )
    }
}