package com.anushka.themusicappui.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import  com.anushka.themusicappui.R

@Composable
fun Browse() {
    val categories = listOf(
        Category("Party jam", R.drawable.party),
        Category("Long Drive Playlist", R.drawable.drive),
        Category("Workout", R.drawable.workout),
        Category("Festives", R.drawable.festive),
        Category("Romantic", R.drawable.rose),
        Category("Old School", R.drawable.oldschool)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(categories) { cat ->
            BrowserItem(cat = cat)
        }
    }
}
