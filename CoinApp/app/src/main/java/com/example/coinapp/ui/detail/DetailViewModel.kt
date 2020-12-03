package com.example.coinapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.api.GenericResult
import com.example.coinapp.models.CoinModel
import com.example.coinapp.repository.CoinRepository
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(private val coinRepository: CoinRepository) :
    ViewModel() {

    private val coinDetailMutableLiveData = MutableLiveData<GenericResult<CoinModel?>>()

    val coinDetailLiveData: LiveData<GenericResult<CoinModel?>>
        get() = coinDetailMutableLiveData

    fun getCoinDetail(id: Int) {
        coinDetailMutableLiveData.value = GenericResult.Loading()
        viewModelScope.launch {
            coinRepository.getCoinDetail(id).let { response ->
                if (response.isSuccessful) {
                    coinDetailMutableLiveData.value = GenericResult.Success(response.body())
                } else {
                    coinDetailMutableLiveData.value = GenericResult.Failure(response.errorBody())
                }
            }
        }
    }
}