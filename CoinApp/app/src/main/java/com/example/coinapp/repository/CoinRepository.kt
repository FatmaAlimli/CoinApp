package com.example.coinapp.repository

import com.example.coinapp.api.CoinApi
import com.example.coinapp.models.CoinModel
import retrofit2.Response
import javax.inject.Inject

class CoinRepository @Inject constructor(private val coinApi: CoinApi) {

    suspend fun getCoin(): Response<CoinModel> {
        return coinApi.getCoin()
    }

    suspend fun getCoinDetail(id: Int): Response<CoinModel> {
        return coinApi.getCoinDetail(id)
    }
}