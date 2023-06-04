package com.zenith.projectdelta.data

import com.zenith.projectdelta.data.network.models.Product
import com.zenith.projectdelta.data.network.models.ProductModel
import com.zenith.projectdelta.utils.Response
import retrofit2.http.GET


interface ApiService {

    @GET("products")
    suspend fun getProductList(): ProductModel
}