package com.example.myapplication.data.retrofit

import com.example.myapplication.data.pojo.RickyMortyDataClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApi {
    @GET("character")
  suspend  fun getAllCharacters(): Response<RickyMortyDataClass>
}