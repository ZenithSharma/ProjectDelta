package com.zenith.projectdelta.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenith.projectdelta.data.network.models.Product
import com.zenith.projectdelta.data.network.models.ProductModel
import com.zenith.projectdelta.data.network.repository.ProductRepository
import com.zenith.projectdelta.utils.Response
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainViewModel(private val _productRepository: ProductRepository) : ViewModel() {
    private val _productLiveData = MutableLiveData<Response<ProductModel>>()

    val productList: LiveData<Response<ProductModel>>
        get() = _productLiveData

    fun getProduct(){
        viewModelScope.launch {
            _productLiveData.postValue(Response.Loading())
            _productRepository.getRemoteProductList().catch {
                _productLiveData.postValue(Response.Error("Something went wrong"))
            }.collect() {
                _productLiveData.postValue(Response.Success(it))
            }
        }
    }
}