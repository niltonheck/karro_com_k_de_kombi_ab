package com.example.mycomposeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mycomposeapp.model.ProductModel
import com.example.mycomposeapp.ui.components.Header
import com.example.mycomposeapp.ui.components.StoryListing
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun HomeScreen(products: MutableState<List<ProductModel>> = mutableStateOf(listOf(
    ProductModel("https://via.placeholder.com/100", "Kombi", 99999.99, false, "Recife, PE", 0)
)), analytics: FirebaseAnalytics, listingType: Boolean = false) {
    Column {
        Header()
        StoryListing(products = products, analytics, listingType = listingType)
    }
}