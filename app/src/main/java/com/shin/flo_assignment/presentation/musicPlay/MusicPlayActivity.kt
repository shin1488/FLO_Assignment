package com.shin.flo_assignment.presentation.musicPlay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.shin.flo_assignment.R
import com.shin.flo_assignment.data.RetrofitInstance
import com.shin.flo_assignment.data.dto.MusicInfo
import com.shin.flo_assignment.data.repository.MusicInfoRepository
import com.shin.flo_assignment.databinding.ActivityMusicPlayBinding
import kotlinx.coroutines.launch

class MusicPlayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicPlayBinding
    private lateinit var musicPlayViewModel: MusicPlayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music_play)
        binding.lifecycleOwner = this

        val api = RetrofitInstance.api

        val musicInfoRepository = MusicInfoRepository(api)
        val musicPlayViewModelFactory = MusicPlayViewModel.MusicPlayViewModelFactory(musicInfoRepository)
        musicPlayViewModel = ViewModelProvider(this, musicPlayViewModelFactory)[MusicPlayViewModel::class.java]

        binding.musicPlayViewModel = musicPlayViewModel

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    musicPlayViewModel.musicInfo.collect{ musicInfo ->

                    }
                }
            }
        }
    }
}