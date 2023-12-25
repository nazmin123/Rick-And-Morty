package com.example.myapplication.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.pojo.Character
import com.example.myapplication.repository.CharacterRepository
import kotlinx.coroutines.launch


class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {


    init {
        getCharacters()
    }


    private fun getCharacters() {

        viewModelScope.launch {
            repository.getAllCharacters()
        }
    }


    fun observeCharacters(): LiveData<List<Character>> {
        return repository.characters
    }

    fun observeErrorMessage(): LiveData<String> {
        return repository.errorMessage
    }

    fun observeLoading(): LiveData<Boolean> {
        return repository.loading
    }

    fun observeNetwork(): LiveData<Boolean> {
        return repository.network
    }
}
