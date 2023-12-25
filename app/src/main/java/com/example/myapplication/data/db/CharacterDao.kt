package com.example.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.pojo.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacter(): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(character: List<Character>)
}