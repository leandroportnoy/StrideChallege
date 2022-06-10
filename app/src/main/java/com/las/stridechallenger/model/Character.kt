package com.las.stridechallenger.model

import com.google.gson.annotations.SerializedName

class Character(
    @SerializedName("name")
    var name: String,
    @SerializedName("image")
    var image: String
)