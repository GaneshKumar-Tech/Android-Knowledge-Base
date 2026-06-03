package com.example.retrofitmvvm

import com.example.retrofitmvvm.model.Product
import com.example.retrofitmvvm.network.ApiService
import com.example.retrofitmvvm.repository.ProductRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private lateinit var apiService: ApiService

    private lateinit var repository: ProductRepository

    @Before
    fun setup() {

        apiService = mockk()

        repository = ProductRepository(apiService)
    }

    @Test
    fun `getProducts returns products from api`() = runTest {

        val products = listOf(
            Product(
                id = 1,
                title = "Nike Shoes",
                price = 2999.0,
                description = "Running Shoes",
                image = "image_url"
            )
        )

        coEvery {
            apiService.getProducts()
        } returns products

        val result = repository.getProducts()

        assertEquals(
            products,
            result
        )

        coVerify(exactly = 1) {
            apiService.getProducts()
        }
    }
}