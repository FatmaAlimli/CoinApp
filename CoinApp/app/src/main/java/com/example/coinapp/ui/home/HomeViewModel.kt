package com.example.coinapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.api.GenericResult
import com.example.coinapp.models.CoinModel
import com.example.coinapp.repository.CoinRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val coinRepository: CoinRepository) :
    ViewModel() {

    private val coinMutableLiveData = MutableLiveData<GenericResult<CoinModel?>>()

    val coinLiveData: LiveData<GenericResult<CoinModel?>>
        get() = coinMutableLiveData

    fun getCoin() {
        coinMutableLiveData.value = GenericResult.Loading()
        viewModelScope.launch {
            coinRepository.getCoin().let { response ->
                if (response.isSuccessful) {
                    coinMutableLiveData.value = GenericResult.Success(response.body())
                } else {
                    coinMutableLiveData.value = GenericResult.Failure(response.errorBody())
                }
            }
        }
    }

}