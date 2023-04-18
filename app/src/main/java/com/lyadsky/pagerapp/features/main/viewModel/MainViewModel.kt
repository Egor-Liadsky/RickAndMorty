package com.lyadsky.pagerapp.features.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lyadsky.pagerapp.data.CharacterPager
import com.lyadsky.pagerapp.data.entity.Result
import com.lyadsky.pagerapp.data.service.CharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class MainViewModel : ViewModel() {
    abstract fun getCharacters(page: Int)
    abstract val character: StateFlow<List<Result>?>
    abstract val pager: StateFlow<PagingData<Result>>
}

class MainViewModelImpl(
    private val characterPager: CharacterPager
) : MainViewModel(), KoinComponent {
    private val characterService: CharacterService by inject()

    override val pager = Pager(
        PagingConfig(prefetchDistance = 2, pageSize = 2),
        initialKey = 1,
        pagingSourceFactory = { characterPager }).flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private val _character = MutableStateFlow<List<Result>?>(null)
    override val character = _character.asStateFlow()

    override fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//            _character.update { characterService.getCharacters(page) }
        }
    }
}
