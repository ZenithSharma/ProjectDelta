package com.zenith.projectdelta.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zenith.projectdelta.data.network.repository.ProductRepository

class MainViewModelFactory(private val _productRepository: ProductRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(_productRepository) as T
    }
}