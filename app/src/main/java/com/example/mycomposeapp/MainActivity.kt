package com.example.mycomposeapp

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Surface
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.datasource.ProductsDataSource
import com.example.mycomposeapp.ui.screens.AppScreen
import com.example.mycomposeapp.ui.screens.HomeScreen
import com.example.mycomposeapp.ui.screens.SplashScreen
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme
import com.example.mycomposeapp.ui.theme.Purple700
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

data class AppState(
    var loading: Boolean
)

class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appState: MutableState<AppState> = mutableStateOf(AppState(true))

        firebaseAnalytics = Firebase.analytics

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d("Remote Cconfig", "Config params updated: $updated")
                    Toast.makeText(this, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Fetch failed",
                        Toast.LENGTH_SHORT).show()
                }

                appState.value = AppState(loading = false)
            }

        setContent {
            AppScreen(appState = appState, analytics = firebaseAnalytics, remoteConfig = remoteConfig)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeAppTheme {
        HomeScreen(analytics = Firebase.analytics)
    }
}