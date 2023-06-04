package com.zenith.projectdelta.data.network.repository

import android.util.Log
import com.zenith.projectdelta.data.ApiService
import com.zenith.projectdelta.data.network.models.Product
import com.zenith.projectdelta.data.network.models.ProductModel
import com.zenith.projectdelta.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteProductDataSource(
    private val apiService: ApiService, private val refreshIntervalMs: Long = 5000
) {
    fun getRemoteProductList(): Flow<ProductModel> = flow {
        while (true) {
            //Fetch product from network
            val networkResponse = apiService.getProductList()
            emit(networkResponse)
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.IO)
}