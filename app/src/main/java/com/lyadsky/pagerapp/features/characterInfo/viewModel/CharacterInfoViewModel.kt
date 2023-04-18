package com.lyadsky.pagerapp.features.characterInfo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyadsky.pagerapp.data.entity.Character
import com.lyadsky.pagerapp.data.service.CharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class CharacterInfoViewModel : ViewModel() {
    abstract val character: StateFlow<Character>?
    abstract fun getCharacter(id: Int)
}

class CharacterInfoViewModelImpl(
    private val characterService: CharacterService
) : CharacterInfoViewModel() {

    private val _character = MutableStateFlow<Character>(Character(info = null, results = null))
    override val character: StateFlow<Character> = _character.asStateFlow()

    override fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _character.update { characterService.getCharacter(id) }
        }
    }
}
