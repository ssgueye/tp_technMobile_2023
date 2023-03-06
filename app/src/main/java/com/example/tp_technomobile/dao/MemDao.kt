package com.example.tp_technomobile.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp_technomobile.model.Mem

@Dao
interface MemDao {

    @Query("SELECT * FROM Memes")
    fun getAll(): List<Mem>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(mems: List<Mem>)

}