package com.example.mycomposeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycomposeapp.ui.screens.AppScreen
import com.example.mycomposeapp.ui.screens.HomeScreen
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

data class AppState(
    var loading: Boolean
)

class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logFirebaseToken()

        val appState: MutableState<AppState> = mutableStateOf(AppState(true))

        firebaseAnalytics = Firebase.analytics

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 10
        }

        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
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

private fun logFirebaseToken() {
    FirebaseInstallations.getInstance().getToken(/* forceRefresh */ true)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Installations", "Installation auth token: " + task.result?.token)
            } else {
                Log.e("Installations", "Unable to get Installation auth token")
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