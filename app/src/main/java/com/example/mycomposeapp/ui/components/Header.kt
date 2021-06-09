package com.example.mycomposeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.R

@Composable
fun Header() {
    Surface(modifier = Modifier
        .background(colorResource(id = R.color.purple_200))
        .padding(16.dp)
        .fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.background(colorResource(id = R.color.purple_200)),
            color = Color.White)
    }
}