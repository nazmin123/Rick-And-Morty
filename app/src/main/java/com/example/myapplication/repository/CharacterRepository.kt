package com.example.myapplication.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.db.CharacterDatabase
import com.example.myapplication.data.pojo.Character
import com.example.myapplication.data.retrofit.RetrofitInstance
import com.example.myapplication.utils.NetworkUtils

class CharacterRepository(
    private val characterDatabase: CharacterDatabase,
    private val applicationContext: Context
) {
    var characters: MutableLiveData<List<Character>> = MutableLiveData<List<Character>>()
    val errorMessage = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()
    var network = MutableLiveData<Boolean>()


    suspend fun getAllCharacters() {
        val character = characterDatabase.getCharacterDao().getAllCharacter()

        if (character.isNotEmpty()) {
            loading.value = false
            characters.postValue(character)

        } else {
            if (NetworkUtils.isInternetAvailable(applicationContext)) {
                loading.value = false
                network.value = true
                val result = RetrofitInstance.characterApi.getAllCharacters()
                Log.v("EmulatorError", result.message())
                Log.v("EmulatorError", result.body().toString())
                Log.v("EmulatorError", result.isSuccessful.toString())
                Log.v("EmulatorError", result.code().toString())
                Log.v("EmulatorError", result.errorBody().toString())
                if (result.body() != null) {
                    characterDatabase.getCharacterDao().insertAll(result.body()!!.results)
                    characters.postValue(result.body()!!.results)
                } else
                    onError("Error : ${result.message()}")


            } else {
                loading.value = false
                network.value = false
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        Log.v("errorMessage", message)
        loading.value = false
    }

}