package com.shutterfly.pixabaygallery.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.shutterfly.pixabaygallery.adapters.GalleryAdapter
import com.shutterfly.pixabaygallery.databinding.ActivityGalleryBinding
import com.shutterfly.pixabaygallery.viewmodels.GalleryViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    private val viewModel by viewModels<GalleryViewModel>()
    private val galleryAdapter = GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        loadInitialImages()
    }

    private fun setupUI() {
        // setup toolbar
        setSupportActionBar(binding.toolbar)

        // setup recycler view related stuff
        with(binding.recycler) {
            this.adapter = galleryAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun loadInitialImages() {
        lifecycleScope.launch {
            viewModel.images.collectLatest { data ->
                galleryAdapter.submitData(data)
            }
        }
    }
}