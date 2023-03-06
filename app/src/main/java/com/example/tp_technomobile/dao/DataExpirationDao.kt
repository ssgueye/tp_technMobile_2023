package com.example.tp_technomobile.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tp_technomobile.model.DataExpiration

@Dao
interface DataExpirationDao {
    @Insert
    fun insertDataExpiration(dataExpiration: DataExpiration)

    @Update
    fun update(dataExpiration: DataExpiration)

    @Query("SELECT * FROM DataExpiration d WHERE d.id = 1")
    fun get():DataExpiration
}