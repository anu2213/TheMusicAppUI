package com.anushka.themusicappui.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anushka.themusicappui.Lib
import com.anushka.themusicappui.libraries


@Composable
fun Library(){
    LazyColumn{
        items(libraries){
            lib->
            libItem(lib=lib)

        }
    }
}
@Composable
fun libItem(lib: Lib){
    Column{
        Row(modifier=Modifier
            .fillMaxWidth()
            .padding(vertical=16.dp),
            horizontalArrangement=Arrangement.SpaceBetween){
            Row{
                Icon(
                    painter=  painterResource(id=lib.icon),
                    modifier=Modifier
                        .padding(horizontal=8.dp)
                        .size(32.dp),
                    contentDescription=lib.name
                    )
                Text(text=lib.name)
            }
            Icon(imageVector= Icons.Default.KeyboardArrowRight,contentDescription="Arrow Right")
        }
        Divider(color= Color.LightGray)

    }

}