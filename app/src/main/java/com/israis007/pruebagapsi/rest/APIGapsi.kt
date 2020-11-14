package com.israis007.pruebagapsi.rest

import com.israis007.pruebagapsi.rest.responses.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API = "/demo-gapsi"
private const val SEARCH = "query"

interface APIGapsi {


    @GET("$API/search")
    suspend fun getProducts(@Query(SEARCH) query: String): Response<ProductResponse>

}