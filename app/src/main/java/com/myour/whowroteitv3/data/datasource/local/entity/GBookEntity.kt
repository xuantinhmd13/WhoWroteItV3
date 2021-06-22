package com.myour.whowroteitv3.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gbook")
data class  GBookEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "authors")
    val authors: String,

    @ColumnInfo(name = "published_date")
    val publishedDate: String,

    @ColumnInfo(name = "small_thumbnail")
    val smallThumbnail: String,
)