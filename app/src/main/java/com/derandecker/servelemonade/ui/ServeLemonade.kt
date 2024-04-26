package com.derandecker.servelemonade.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.derandecker.servelemonade.MainViewModel
import com.derandecker.servelemonade.R
import com.derandecker.servelemonade.ui.theme.ServeLemonadeTheme

@Composable
fun ServeLemonade(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        AddClearButtons(
            modifier = modifier,
            clearList = { viewModel.clearLemonadeList() },
            addToList = { viewModel.addToLemonadeList() }
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp), // set the min size for each grid item
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(viewModel.lemonadeList.size) { item ->
                GridItem(item = item)
            }
        }
    }
}

@Composable
private fun AddClearButtons(
    modifier: Modifier,
    clearList: () -> Unit,
    addToList: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(
            onClick = { addToList() }
        ) {
            Text(text = "Add Lemonade")
        }
        Button(
            onClick = { clearList() }
        ) {
            Text(text = "Clear")
        }
    }
}

@Composable
fun GridItem(modifier: Modifier = Modifier, item: Int) {
    Box(contentAlignment = Alignment.TopEnd) {
        Image(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = R.drawable.lemonade),
            // lemonade image from https://bellyfull.net/homemade-lemonade-recipe/
            contentDescription = null
        )
        Text(
            text = item.toString(),
            fontWeight = FontWeight.Black,
            modifier = modifier.padding(end = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServeLemonadeTheme {
        ServeLemonade()
    }
}
