package com.example.tp_technomobile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp_technomobile.dao.DataExpirationDao
import com.example.tp_technomobile.dao.MemDao
import com.example.tp_technomobile.model.DataExpiration
import com.example.tp_technomobile.model.Mem

@Database(entities = [Mem::class, DataExpiration::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun memDao():MemDao
    abstract fun dataExpirationTimeDao():DataExpirationDao
}