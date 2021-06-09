package com.example.mycomposeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.model.ProductModel
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun StoryListing(products: MutableState<List<ProductModel>> = mutableStateOf(listOf(
    ProductModel("https://via.placeholder.com/100", "Kombi", 99999.99, false, "Recife, PE", 0)
)), analytics: FirebaseAnalytics, listingType: Boolean = false) {
    LazyColumn {
        products.value.map { product ->
            item {
                Product(product.name, product.image, product.featured, product.price, product.location, product.visitors, listingType, analytics)

                Spacer(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Gray))
            }
        }
    }
}