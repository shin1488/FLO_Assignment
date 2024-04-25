package com.shin.flo_assignment.presentation.musicPlay

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.shin.flo_assignment.data.dto.MusicInfo
import com.shin.flo_assignment.data.repository.MusicInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MusicPlayViewModel(private val repository: MusicInfoRepository): ViewModel() {
    private val _musicInfo = MutableStateFlow<MusicInfo?>(null)
    val musicInfo: StateFlow<MusicInfo?> = _musicInfo

    init {
        fetchMusicInfo()
        updateMusicInfo()
    }

    private fun fetchMusicInfo() {
        viewModelScope.launch {
            _musicInfo.value = repository.getMusicInfo()
        }
    }

    private fun updateMusicInfo() {
        viewModelScope.launch {
            musicInfo.collect { info ->
                if (info != null) {
                    Log.d("MusicInfo", "Singer: ${info.singer}")
                    Log.d("MusicInfo", "Album: ${info.album}")
                    Log.d("MusicInfo", "Title: ${info.title}")
                    Log.d("MusicInfo", "duration: ${info.duration}")
                    Log.d("MusicInfo", "Image URL: ${info.image}")
                    Log.d("MusicInfo", "Lyrics: ${info.lyrics}")
                } else println("fuckyou")
            }
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