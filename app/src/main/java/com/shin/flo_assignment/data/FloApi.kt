package com.shin.flo_assignment.data

import com.shin.flo_assignment.data.dto.MusicInfo
import retrofit2.http.GET

interface FloApi {
    @GET("2020-flo/song.json")
    suspend fun getMusicInfos(): MusicInfo
}