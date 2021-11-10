package com.shutterfly.pixabaygallery.network

import com.shutterfly.pixabaygallery.models.GalleryData
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {

    @GET("?key=12175339-7048b7105116d7fa1da74220c&image_type=photo")
    suspend fun loadImagesByKey(@Query("per_page") loadSize: Int, @Query("page") page: Int, @Query("q") keyword: String): GalleryData
}