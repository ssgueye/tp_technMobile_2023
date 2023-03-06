package com.example.tp_technomobile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DataExpiration")
data class DataExpiration(
    @PrimaryKey var id:Int,
    @ColumnInfo(name = "last_loaded_time") var lastLoadedTime:Long
)
