package com.example.tp_technomobile.data

import com.example.tp_technomobile.model.Mem
import com.google.gson.annotations.SerializedName

data class MemData(
    @SerializedName("memes" )val memes: List<Mem>
)
