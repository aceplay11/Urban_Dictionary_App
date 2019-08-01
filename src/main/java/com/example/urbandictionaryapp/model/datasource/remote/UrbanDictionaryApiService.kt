package com.example.urbandictionaryapp.model.datasource.remote

import com.example.urbandictionaryapp.model.datasource.UrbanDictionary.UrbanDictionaryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com/"
const val PATH = "define"
const val HOST_HEADER = "x-rapidapi-host"
const val HOST = "mashape-community-urban-dictionary.p.rapidapi.com"
const val KEY_HEADER = "x-rapidapi-key"
const val KEY = "a105a6869bmsh15bbda462f97953p18a6b5jsn630e0ad12b6a"
interface AtlUrbanDictionaryApiService {
    @GET(PATH)
    fun userEntry(@Query("term") input: String,
                  @Header(HOST_HEADER) host: String = HOST,
                  @Header(KEY_HEADER) key: String = KEY ) : Call<UrbanDictionaryResponse>
}

