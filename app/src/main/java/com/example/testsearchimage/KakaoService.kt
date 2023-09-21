package com.example.testsearchimage

import android.provider.Contacts.SettingsColumns.KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoService {
    @GET("image")
    fun getKakaoImage(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String
    ): Call<KakaoResponse>
}