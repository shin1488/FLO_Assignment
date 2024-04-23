package com.shin.flo_assignment.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.shin.flo_assignment.R
import com.shin.flo_assignment.presentation.musicPlay.MusicPlayActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val toMusicPlayActivityIntent = Intent(this@SplashActivity, MusicPlayActivity::class.java)
            startActivity(toMusicPlayActivityIntent)
            finish()
        }, 2000)
    }
}