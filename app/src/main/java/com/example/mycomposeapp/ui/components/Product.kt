package com.example.mycomposeapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.ui.theme.*
import com.google.accompanist.coil.rememberCoilPainter
import com.google.firebase.analytics.FirebaseAnalytics
import java.text.NumberFormat
import java.util.*

@Composable
fun Product(title: String, image: String, featured: Boolean = false, price: Double, location: String, visitors: Int, newListingType: Boolean, analytics: FirebaseAnalytics) {
    val color = getBackgroundColor(featured)

    Row(
        modifier = Modifier
            .clickable { if(featured) onClick(analytics) }
            .fillMaxWidth()
            .background(color)
            .padding(16.dp),
    ) {
        if ( featured ) {
            Image(
                painter = rememberCoilPainter(image),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(text = title,
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Text(text = formatToCurrency(price = price),
                style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = location,
                style = MaterialTheme.typography.body2)
            if ( hasVisitorsShown(newListingType ,featured) ) {
                VisitorsDisplay(visitors = visitors)
            }
        }
    }
}

fun formatToCurrency(price: Double): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.setMaximumFractionDigits(0)
    format.setCurrency(Currency.getInstance("BRL"))

    return format.format(price)
}

@Composable
fun VisitorsDisplay(visitors: Int) {
    Column(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
        Text(
            text = "$visitors visitas recentes",
            style = MaterialTheme.typography.caption)
    }
}

fun onClick(analytics: FirebaseAnalytics) {
    analytics.logEvent("click_on_feature", null)
    Log.d("CLICK", "Clicou em um item da listagem")
}

fun getBackgroundColor(featured: Boolean): Color {
    return if (featured) Yellow200 else  White;
}

fun hasVisitorsShown(newListingType: Boolean, featured: Boolean): Boolean {
    return newListingType && featured
}