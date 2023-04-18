package com.lyadsky.pagerapp.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
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
        LazyColumn {
            items(items = characters, key = { it.id }) { character ->
                CharacterItem(character = character!!)
            }
        }
    }
}

@Composable
fun CharacterItem(character: Result) {
    Card(Modifier.padding(16.dp), elevation = 5.dp, shape = RoundedCornerShape(10.dp)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)) {
            Text(text = character.id.toString(), style = TextStyle(
                fontSize = 20.sp
            ))
            Text(text = character.location.toString(), style = TextStyle(
                fontSize = 20.sp
            ))
            Text(text = character.image.toString(), style = TextStyle(
                fontSize = 16.sp
            ))
        }
    }
}
