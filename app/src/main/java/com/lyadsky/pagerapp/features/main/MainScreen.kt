package com.lyadsky.pagerapp.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.lyadsky.pagerapp.data.entity.Result
import com.lyadsky.pagerapp.features.main.viewModel.MainViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun MainScreen(viewModel: MainViewModel = get(named("MainViewModel"))) {
    val characters = viewModel.pager.collectAsLazyPagingItems()

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBFE))
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(characters.itemCount) { index ->
                CharacterItem(
                    character = characters[index]!!,
                    onClick = {
                        viewModel.onCharacterClick(characters[index]!!.id)
                    })
            }
        }
    }
}

@Composable
fun CharacterItem(character: Result, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(16.dp)
            .width(150.dp)
            .height(200.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 3.dp,
            hoveredElevation = 5.dp,
            pressedElevation = 10.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                alignment = Alignment.TopCenter
            )
            Text(
                text = character.name, style = TextStyle(
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
