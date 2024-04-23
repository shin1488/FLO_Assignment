package com.shin.flo_assignment.data.repository

import android.util.Log
import com.shin.flo_assignment.data.FloApi
import com.shin.flo_assignment.data.dto.MusicInfo
import java.lang.Exception

class MusicInfoRepository(private val floApi: FloApi) {
    suspend fun getMusicInfos(): MusicInfo {
        return try {
            floApi.getMusicInfos()
        } catch (e: Exception) {
            e.message?.let { Log.e("fetch exception", it) }
            throw e
        }
    }
}