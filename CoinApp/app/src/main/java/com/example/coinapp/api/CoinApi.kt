package com.example.coinapp.api

import com.example.coinapp.models.CoinModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
    @GET("/v1/public/coins")
    suspend fun getCoin(): Response<CoinModel>

    @GET("/v1/public/coins")
    suspend fun getCoinDetail(@Query("id") id: Int): Response<CoinModel>
}