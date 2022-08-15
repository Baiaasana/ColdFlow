package com.example.adtajstumag.fragments

import android.util.Log.d
import androidx.lifecycle.*
import com.example.adtajstumag.LatestInfoUiState
import com.example.adtajstumag.data.ItemModel
import com.example.adtajstumag.Utils.RetrofitClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InfoViewModel : ViewModel() {

    private val _infoState =
        MutableStateFlow<LatestInfoUiState<List<ItemModel.Item>>>(LatestInfoUiState.Loader(true))
    val infoState = _infoState.asStateFlow()

    fun collect(){
        viewModelScope.launch {
            getInfo().collect{
                _infoState.value = it
            }
        }
    }

    private fun getInfo(): Flow<LatestInfoUiState<List<ItemModel.Item>>> {

        return flow {
            try {
                val response = RetrofitClient.retrofitBuilder.getInfo()
                when {
                    response.isSuccessful -> {
                        val body = response.body()
                        emit(LatestInfoUiState.Success(body!!.content))
                    }
                    else -> {
                        val errorBody = response.errorBody()
                        emit(LatestInfoUiState.Error(errorBody?.toString() ?: " "))
                    }
                }
            } catch (e: Throwable) {
                emit(LatestInfoUiState.Error("error"))
            }
            emit(LatestInfoUiState.Loader(false))
        }
    }
}


