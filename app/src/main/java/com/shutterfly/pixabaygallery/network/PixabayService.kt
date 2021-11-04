package com.shutterfly.pixabaygallery.network

import com.shutterfly.pixabaygallery.models.GalleryData
import com.shutterfly.pixabaygallery.viewmodels.GalleryViewModel.Companion.DEFAULT_PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
    @GET("?key=12175339-7048b7105116d7fa1da74220c&image_type=photo&per_page=$DEFAULT_PAGE_SIZE")
    suspend fun loadImages(@Query("page") page: Int): GalleryData

    @GET("?key=12175339-7048b7105116d7fa1da74220c&image_type=photo&per_page=$DEFAULT_PAGE_SIZE")
    suspend fun loadImagesByKey(@Query("q") keyword: String, @Query("page") page: Int): GalleryData
}