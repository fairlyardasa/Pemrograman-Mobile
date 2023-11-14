package com.example.p11_retrovidapp.network

import com.example.p11_retrovidapp.model.News
import com.example.p11_retrovidapp.model.NewsList
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("games")
    fun getAllNews(): Call<List<News>>
}