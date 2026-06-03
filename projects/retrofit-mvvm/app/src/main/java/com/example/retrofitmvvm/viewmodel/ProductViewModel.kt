package com.example.retrofitmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.common.Resource
import com.example.retrofitmvvm.model.Product
import com.example.retrofitmvvm.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products =
        MutableStateFlow<Resource<List<Product>>>(
            Resource.Loading
        )

    val products: StateFlow<Resource<List<Product>>> =
        _products.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {

        viewModelScope.launch {

            _products.value = Resource.Loading

            try {

                val response = repository.getProducts()

                _products.value =
                    Resource.Success(response)

            } catch (e: Exception) {

                _products.value =
                    Resource.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }

    fun refreshProducts() {
        getProducts()
    }
}