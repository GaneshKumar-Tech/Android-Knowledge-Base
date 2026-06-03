package com.example.retrofitmvvm.view.compose.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.retrofitmvvm.model.Product

@Composable
fun ProductCard(

    product: Product,

    onAddToCart: () -> Unit

) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ) {

        Column(

            modifier = Modifier.padding(12.dp)

        ) {

            AsyncImage(

                model = product.image,

                contentDescription = product.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),

                contentScale = ContentScale.Crop

            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "₹ ${product.price}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(text = "Add to Cart")

            }
        }
    }
}