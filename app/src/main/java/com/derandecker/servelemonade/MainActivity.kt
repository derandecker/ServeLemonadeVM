package com.derandecker.servelemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.derandecker.servelemonade.ui.theme.ServeLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServeLemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ServeLemonade()
                }
            }
        }
    }
}

@Composable
fun ServeLemonade(modifier: Modifier = Modifier) {
    val lemonadeList = remember {
        mutableStateListOf<Int>()
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(
                onClick = {
                    if (lemonadeList.isNotEmpty()) {
                        lemonadeList.add(lemonadeList.last() + 1)
                    } else {
                        lemonadeList.add(1)
                    }
                }
            ) {
                Text(text = "Add Lemonade")
            }
            Button(
                onClick = {
                    lemonadeList.clear()
                }
            ) {
                Text(text = "Clear")
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp), // set the min size for each grid item
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(lemonadeList.size) { item ->
                GridItem(item = item)
            }
        }
    }
}

@Composable
fun GridItem(modifier: Modifier = Modifier, item: Int) {
    Box (contentAlignment = Alignment.TopEnd) {
        Image(
            modifier = Modifier.clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = R.drawable.lemonade),
            // lemonade image from https://bellyfull.net/homemade-lemonade-recipe/
            contentDescription = null
        )
        Text(text = item.toString(),
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
