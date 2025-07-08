package com.anushka.themusicappui.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.ui.unit.Dp
import com.anushka.themusicappui.R


data class Category(val name: String, val imageRes: Int)

@Composable
fun Home() {
    val categories = listOf(
        Category("Party jam", R.drawable.party),
        Category("Long Drive Playlist", R.drawable.drive),
        Category("Workout", R.drawable.workout),
        Category("Festives", R.drawable.festive),
        Category("Romantic", R.drawable.rose),
        Category("Old School", R.drawable.oldschool)
    )

    val grouped = listOf("New Release", "Top Rated", "Favorites").groupBy { it[0] }

    LazyColumn {
        grouped.entries.forEach { entry ->
            stickyHeader {
                Text(
                    text = entry.value[0],
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                LazyRow {
                    items(categories) { cat ->
                        BrowserItem(cat = cat)
                    }
                }
            }
        }
    }
}

@Composable
fun BrowserItem(cat: Category) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Box {
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = cat.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xAA000000))
                        )
                    )
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = cat.name,
                    modifier = Modifier.padding(8.dp),
                    color = Color.White
                )
            }
        }
    }
}
