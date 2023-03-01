package com.example.tp_technomobile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mem(
    @PrimaryKey var id:Int,
    @ColumnInfo var name: String? = null,
    @ColumnInfo var url: String? = null
)
