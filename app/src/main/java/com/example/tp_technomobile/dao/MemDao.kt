package com.example.tp_technomobile.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tp_technomobile.model.Mem

@Dao
interface MemDao {

    @Query("SELECT * FROM mem")
    fun getAll(): List<Mem>
    @Insert
    fun insertAll(mems: List<Mem>)

}