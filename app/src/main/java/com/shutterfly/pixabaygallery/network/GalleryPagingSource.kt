package com.shutterfly.pixabaygallery.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shutterfly.pixabaygallery.models.GalleryItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleryPagingSource : PagingSource<Int, GalleryItem>() {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val pixabayService by lazy {
        retrofit.create(PixabayService::class.java)
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        val currentPage = params.key ?: 1
        val previousPage = if (currentPage > 1) currentPage - 1 else null
        val nextPage = currentPage + 1

        return try {
            val images = pixabayService.loadImages(currentPage)
            LoadResult.Page(images.galleryItems, previousPage, nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}