package com.example.retrofitmvvm.view.compose.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.retrofitmvvm.model.Product
import com.example.retrofitmvvm.navigation.Screen
import com.example.retrofitmvvm.ui.components.CircularProgressLoader
import com.example.retrofitmvvm.common.Resource
import com.example.retrofitmvvm.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavHostController
) {

    val viewModel: ProductViewModel = hiltViewModel()

    val productState by
    viewModel.products.collectAsStateWithLifecycle()

    /*var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        delay(3000)
        isLoading = false
    }*/

    var favoriteCount by remember {
        mutableIntStateOf(3)
    }

    var cartCount by remember {
        mutableIntStateOf(2)
    }

    val products = listOf(
        Product(
            id = 1,
            title = "Nike Shoes",
            price = 2999.0,
            description = "Running shoes",
            image = "https://picsum.photos/200"
        ),
        Product(
            id = 2,
            title = "Smart Watch",
            price = 4999.0,
            description = "Fitness watch",
            image = "https://picsum.photos/201"
        )
    )

    //var products = emptyList<Product>()

    Scaffold(

        modifier = Modifier.fillMaxSize(),

        topBar = {

            DashboardTopBar(

                favoriteCount = favoriteCount,
                cartCount = cartCount,

                onFavoriteClick = {

                    navController.navigate(Screen.Favorite.route) {

                        launchSingleTop = true

                        popUpTo(navController.graph.startDestinationId) {

                            saveState = true

                        }

                        restoreState = true
                    }
                },

                onCartClick = {

                    navController.navigate(Screen.Cart.route) {

                        launchSingleTop = true

                        popUpTo(navController.graph.startDestinationId) {

                            saveState = true

                        }

                        restoreState = true
                    }
                }
            )
        }

    ) { innerPadding ->

        /*if (isLoading){
            CommonLoader()
        }else{
            ShowProducts(innerPadding,products)
        }*/

        when(productState) {

            is Resource.Loading -> {

                CircularProgressLoader()

            }

            is Resource.Success -> {

                val products =
                    (productState as Resource.Success).data

                ShowProducts(
                    innerPadding,
                    products
                )
            }

            is Resource.Error -> {

                val message =
                    (productState as Resource.Error).message

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = message,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }

        /*Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Shopping Screen",
                style = MaterialTheme.typography.headlineMedium
            )
        }*/

    }
}

@Composable
fun ShowProducts(innerPadding: PaddingValues,products: List<Product>){
    if (products.isNotEmpty()){
        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            verticalArrangement = Arrangement.spacedBy(12.dp),

            contentPadding = PaddingValues(12.dp)

        ) {

            items(products) { product ->

                ProductCard(
                    product = product,
                    onAddToCart = {

                    }
                )
            }
        }
    }else{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "No products available",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}