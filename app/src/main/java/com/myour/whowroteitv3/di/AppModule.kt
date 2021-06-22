package com.myour.whowroteitv3.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.GsonBuilder
import com.myour.whowroteitv3.R
import com.myour.whowroteitv3.data.api.IGBookAPI
import com.myour.whowroteitv3.data.database.GBookDatabase
import com.myour.whowroteitv3.util.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGBookDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GBookDatabase::class.java, Const.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideGBookDAO(db: GBookDatabase) = db.GBookDAO

    @Singleton
    @Provides
    fun provideGBookAPI(): IGBookAPI {
        val gson = GsonBuilder().setLenient().create()

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(5_000L, TimeUnit.MILLISECONDS)
            .writeTimeout(5_000L, TimeUnit.MILLISECONDS)
            .connectTimeout(10_000L, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()

        return Retrofit.Builder()
            .baseUrl(Const.BOOK_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IGBookAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image_temp)
            .error(R.drawable.ic_image_fail)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )
}