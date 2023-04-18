package com.lyadsky.pagerapp.features.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lyadsky.pagerapp.data.pagers.CharacterPager
import com.lyadsky.pagerapp.data.entity.Result
import com.lyadsky.pagerapp.navigation.Navigator
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent

abstract class MainViewModel : ViewModel() {
    abstract val character: StateFlow<List<Result>?>
    abstract val pager: StateFlow<PagingData<Result>>

    abstract fun onCharacterClick(id: Int)
}

class MainViewModelImpl(
    private val characterPager: CharacterPager,
    private val navigator: Navigator
) : MainViewModel(), KoinComponent {
    override val pager = Pager(
        PagingConfig(prefetchDistance = 2, pageSize = 2),
        initialKey = 1,
        pagingSourceFactory = { characterPager }).flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private val _character = MutableStateFlow<List<Result>?>(null)
    override val character = _character.asStateFlow()

    override fun onCharacterClick(id: Int) {
        navigator.navigateCharacterInfo(id)
    }
}
