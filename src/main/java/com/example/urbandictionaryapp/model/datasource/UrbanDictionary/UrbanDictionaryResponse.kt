package com.example.urbandictionaryapp.model.datasource.UrbanDictionary


import com.google.gson.annotations.SerializedName

data class UrbanDictionaryResponse(
    @SerializedName("list")
    val list: List<ResponseList>
)