package com.example.myapplication.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
     const val LOCAL_HOST="http://10.0.2.2:8000"
    const val API_URL="https://rickandmortyapi.com/api/"
    val characterApi:CharacterApi by lazy {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }
}