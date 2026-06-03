package com.example.retrofitmvvm.repository

import com.example.retrofitmvvm.network.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getProducts() = apiService.getProducts()

}