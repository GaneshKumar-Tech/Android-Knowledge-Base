package com.example.retrofitmvvm.network

import com.example.retrofitmvvm.model.Product
import retrofit2.http.GET

interface ApiService {

    /*@GET("users")
    suspend fun getUsers(): List<User>*/

    @GET("api/products")
    suspend fun getProducts(): List<Product>

}
