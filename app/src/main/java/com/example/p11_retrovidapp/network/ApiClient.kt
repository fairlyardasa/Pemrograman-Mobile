package com.example.p11_retrovidapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ApiClient {
    fun getInstance(): ApiService {
        val mOkHttpInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient.Builder().addInterceptor(mOkHttpInterceptor).build()

        val builder = Retrofit.Builder().baseUrl("https://the-lazy-media-api.vercel.app/api/").addConverterFactory(GsonConverterFactory.create()).client(mOkHttpClient).build()

        return builder.create(ApiService::class.java)

    }
}