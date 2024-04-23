package com.shin.flo_assignment.presentation.musicPlay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shin.flo_assignment.data.dto.MusicInfo
import com.shin.flo_assignment.data.repository.MusicInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MusicPlayViewModel(private val repository: MusicInfoRepository): ViewModel() {
    private val _musicInfo = MutableStateFlow<MusicInfo?>(null)
    val musicInfo: StateFlow<MusicInfo?> = _musicInfo

    init {
        fetchMusicInfo()
    }

    private fun fetchMusicInfo() {
        viewModelScope.launch {
            _musicInfo.value = repository.getMusicInfos()
        }
    }

    class MusicPlayViewModelFactory(private val repository: MusicInfoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MusicPlayViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MusicPlayViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}