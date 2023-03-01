package com.example.tp_technomobile.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.tp_technomobile.model.Mem

@Dao
interface MemDao {

    @Insert
    fun insertAll(vararg mems:Mem)

}