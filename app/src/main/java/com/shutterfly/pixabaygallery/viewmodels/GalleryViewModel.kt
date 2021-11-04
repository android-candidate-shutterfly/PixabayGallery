package com.shutterfly.pixabaygallery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shutterfly.pixabaygallery.models.GalleryItem
import com.shutterfly.pixabaygallery.network.GalleryPagingSource
import kotlinx.coroutines.flow.Flow

class GalleryViewModel : ViewModel() {

    companion object {
        const val DEFAULT_PAGE_SIZE = 30
    }

    val images: Flow<PagingData<GalleryItem>> = Pager(config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
        GalleryPagingSource()
    }.flow.cachedIn(viewModelScope)
}