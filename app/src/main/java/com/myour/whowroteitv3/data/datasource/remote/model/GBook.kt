package com.myour.whowroteitv3.data.datasource.remote.model


import com.google.gson.annotations.SerializedName

data class GBook(
    @SerializedName("items")
    val items: List<Item>,
)

data class Item(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo,
)

data class VolumeInfo(
    @SerializedName("authors")
    val authors: List<String>,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinks,
    @SerializedName("publishedDate")
    val publishedDate: String,
    @SerializedName("title")
    val title: String,
)

data class ImageLinks(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String,
)

