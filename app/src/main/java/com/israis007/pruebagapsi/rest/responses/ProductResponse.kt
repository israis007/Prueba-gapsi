package com.israis007.pruebagapsi.rest.responses

import com.google.gson.annotations.SerializedName
import com.israis007.pruebagapsi.rest.models.Items

data class ProductResponse(
    @SerializedName("totalResults")     val totalResults    : Long,
    @SerializedName("page")             val page            : Int,
    @SerializedName("items")            val items           : List<Items>
)