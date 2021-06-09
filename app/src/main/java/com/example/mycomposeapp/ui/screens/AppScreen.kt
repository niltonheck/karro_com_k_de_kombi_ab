package com.example.mycomposeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.mycomposeapp.AppState
import com.example.mycomposeapp.datasource.ProductsDataSource
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

@Composable
fun AppScreen(appState: MutableState<AppState>, analytics: FirebaseAnalytics, remoteConfig: FirebaseRemoteConfig) {
    if(!appState.value.loading) {
        HomeScreen(products = ProductsDataSource().getAll(), analytics, remoteConfig.getBoolean("new_listing"))
    } else {
        SplashScreen()
    }
}