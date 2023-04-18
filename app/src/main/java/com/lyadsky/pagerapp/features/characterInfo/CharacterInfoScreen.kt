package com.lyadsky.pagerapp.features.characterInfo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import coil.compose.AsyncImage
import com.lyadsky.pagerapp.features.characterInfo.viewModel.CharacterInfoViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun CharacterInfoScreen(id: Int, viewModel: CharacterInfoViewModel = get(named("CharacterInfoViewModel"))) {
    viewModel.getCharacter(id)

    val character = viewModel.character!!.collectAsState().value.results

    LazyColumn {
        item {
//            Column {
//                character!!.forEach { item ->
//                    AsyncImage(model = item.image, contentDescription = null)
//                    Text(text = item.name)
//                    Text(text = item.created)
//                    Text(text = item.status)
//                    Text(text = item.gender)
//                    Text(text = item.type)
//                    Text(text = item.episode.toString())
//                }
//            }
            Log.e("qwlkeu",character.toString())
        }
    }
}
