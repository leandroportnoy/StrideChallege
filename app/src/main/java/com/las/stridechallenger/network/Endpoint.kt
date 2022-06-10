package com.las.stridechallenger.network

import com.las.stridechallenger.model.Character
import com.las.stridechallenger.model.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("character")
    fun getCharacters(): Call<CharacterResponse>
}