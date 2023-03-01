package com.example.tp_technomobile.data

import com.google.gson.annotations.SerializedName

data class MemData(
    @SerializedName("memes" )val memes: List<Mem>
)
