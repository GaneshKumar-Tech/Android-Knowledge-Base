package com.example.retrofitmvvm

import com.example.retrofitmvvm.common.Resource
import com.example.retrofitmvvm.model.Product
import com.example.retrofitmvvm.repository.ProductRepository
import com.example.retrofitmvvm.viewmodel.ProductViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: ProductRepository

    @Before
    fun setup() {
        repository = mockk()
    }

    @Test
    fun `getProducts returns Success`() = runTest {

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
            repository.getProducts()
        } returns products

        val viewModel = ProductViewModel(repository)

        advanceUntilIdle()

        val state = viewModel.products.value

        assertTrue(state is Resource.Success)

        assertEquals(
            products,
            (state as Resource.Success).data
        )
    }

    @Test
    fun `getProducts returns Error`() = runTest {

        coEvery {
            repository.getProducts()
        } throws Exception("Network Error")

        val viewModel = ProductViewModel(repository)

        advanceUntilIdle()

        val state = viewModel.products.value

        assertTrue(state is Resource.Error)

        assertEquals(
            "Network Error",
            (state as Resource.Error).message
        )
    }
}