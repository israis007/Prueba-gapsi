package com.israis007.pruebagapsi.rest.models

import com.google.gson.annotations.SerializedName

data class Items (
    @SerializedName("id")       val id      : String,
    @SerializedName("rating")   val rating  : Double,
    @SerializedName("price")    val price   : Double,
    @SerializedName("image")    val image   : String,
    @SerializedName("title")    val title   : String
)