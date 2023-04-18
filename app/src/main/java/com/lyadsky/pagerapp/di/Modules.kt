package com.lyadsky.pagerapp.di

import com.lyadsky.pagerapp.data.CharacterPager
import com.lyadsky.pagerapp.data.service.CharacterService
import com.lyadsky.pagerapp.data.service.CharacterServiceImpl
import com.lyadsky.pagerapp.features.main.viewModel.MainViewModel
import com.lyadsky.pagerapp.features.main.viewModel.MainViewModelImpl
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.logging.*
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule())
    }

fun commonModule() = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }
    single {
        HttpClient(OkHttp) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    // Services
    single<CharacterService> { CharacterServiceImpl() }

    // ViewModel
    viewModel<MainViewModel>(named("MainViewModel")) {
        MainViewModelImpl(get())
    }

    //CharacterPager
    single { CharacterPager(get()) }
}
