package com.lyadsky.pagerapp.data.service

import com.lyadsky.pagerapp.data.entity.Character
import com.lyadsky.pagerapp.data.entity.Result
import com.lyadsky.pagerapp.utils.Constants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface CharacterService {
    suspend fun getCharacters(page: Int): Character
}

class CharacterServiceImpl : CharacterService, KoinComponent {

    private val json: Json by inject()
    private val httpClient: HttpClient by inject()

    override suspend fun getCharacters(page: Int): Character {
        val response = httpClient.request(Constants.BASE_URL + "/character/"){
            method = HttpMethod.Get
            parameter("page", page)
        }
        return json.decodeFromString(response.body())
    }
}
