package com.shutterfly.pixabaygallery.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.shutterfly.pixabaygallery.repositories.GalleryRepository

class GalleryViewModel : ViewModel() {

    private companion object {
        private const val DEFAULT_SEARCH_KEYWORD = "android"
    }

    private val repository = GalleryRepository()
    private val _currentKeyword = MutableLiveData(DEFAULT_SEARCH_KEYWORD)

    val imageListObservable = _currentKeyword.switchMap { keyword ->
        repository.searchImages(keyword)
    }

    fun onSearchButtonClicked(keyword: String) {
        if (keyword.isNotBlank()) {
            _currentKeyword.value = keyword
        }
    }
}