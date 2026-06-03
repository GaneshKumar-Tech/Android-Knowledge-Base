package com.example.retrofitmvvm.view.compose.dashboard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.retrofitmvvm.ui.theme.PurplePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(

    favoriteCount: Int,
    cartCount: Int,

    onFavoriteClick: () -> Unit,
    onCartClick: () -> Unit

) {

    TopAppBar(

        title = {

            Row(
                modifier = Modifier.padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp),
                    contentDescription = "Logo"
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "eKart",
                    color = Color.White
                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PurplePrimary
        ),

        actions = {

            // FAVORITE
            IconButton(
                onClick = onFavoriteClick
            ) {

                BadgedBox(

                    badge = {

                        if (favoriteCount > 0) {

                            Badge {

                                Text(
                                    text = if (favoriteCount > 99)
                                        "99+"
                                    else
                                        favoriteCount.toString()
                                )
                            }
                        }
                    }

                ) {

                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        tint = Color.White,
                        contentDescription = "Favorites"
                    )
                }
            }

            // CART
            IconButton(
                onClick = onCartClick
            ) {

                BadgedBox(

                    badge = {

                        if (cartCount > 0) {

                            Badge {

                                Text(
                                    text = if (cartCount > 99)
                                        "99+"
                                    else
                                        cartCount.toString()
                                )
                            }
                        }
                    }

                ) {

                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        tint = Color.White,
                        contentDescription = "Cart"
                    )
                }
            }
        }
    )
}