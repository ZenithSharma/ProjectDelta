package com.zenith.projectdelta.data.network.repository

import com.zenith.projectdelta.data.network.models.Product
import com.zenith.projectdelta.data.network.models.ProductModel
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val _remoteProductDataSource: RemoteProductDataSource) {

    //Fetch product from remote product data source
    fun getRemoteProductList(): Flow<ProductModel> =
        _remoteProductDataSource.getRemoteProductList()
}