package com.zenith.projectdelta.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zenith.projectdelta.R
import com.zenith.projectdelta.data.ApiService
import com.zenith.projectdelta.data.RetrofitHelper
import com.zenith.projectdelta.data.network.repository.ProductRepository
import com.zenith.projectdelta.data.network.repository.RemoteProductDataSource
import com.zenith.projectdelta.databinding.ActivityMainBinding
import com.zenith.projectdelta.ui.adapter.ProductAdapter
import com.zenith.projectdelta.ui.viewmodel.MainViewModel
import com.zenith.projectdelta.ui.viewmodel.MainViewModelFactory
import com.zenith.projectdelta.utils.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        mainViewModel.getProduct()
        bindObservers()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val remoteProductDataSource = RemoteProductDataSource(apiService)
        val productRepository = ProductRepository(remoteProductDataSource);
        mainViewModel = ViewModelProvider(
            this, MainViewModelFactory(productRepository)
        )[MainViewModel::class.java]
    }

    private fun bindObservers() {
        mainViewModel.productList.observe(this, Observer {
            when (it) {
                is Response.Loading -> {
                    Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_LONG).show()
                }
                is Response.Success -> {
                    it.data?.products?.let { productList ->
                        productAdapter.submitList(productList)
                    }
                }
                is Response.Error -> {
                    Toast.makeText(this@MainActivity, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter()
        binding.productList.layoutManager = LinearLayoutManager(this)
        binding.productList.adapter = productAdapter
    }
}