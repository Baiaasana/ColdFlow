package com.example.adtajstumag.fragments

import android.util.Log.d
import androidx.lifecycle.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.adtajstumag.LatestInfoUiState
import com.example.adtajstumag.data.ItemModel
import com.example.adtajstumag.Utils.RetrofitClient
import com.example.adtajstumag.adapters.ItemAdapter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InfoViewModel : ViewModel() {

    private val _infoState =
        MutableStateFlow<LatestInfoUiState<List<ItemModel.Item>>>(LatestInfoUiState.Loader())
    val infoState = _infoState.asStateFlow()


    fun getInfo2(): Flow<LatestInfoUiState<List<ItemModel.Item>>> {

        return flow {

            var items: LatestInfoUiState<List<ItemModel.Item>>
            val response = RetrofitClient.retrofitBuilder.getInfo()
            items = LatestInfoUiState.Loader(true)
            emit(items)

            items = if (response.isSuccessful) {
                LatestInfoUiState.Success(response.body()!!.content)
            } else {
                LatestInfoUiState.Error(response.errorBody().toString())
            }
            emit(items)

            items = LatestInfoUiState.Loader(false)
            emit(items)
        }
    }

    fun collect(adapter: ItemAdapter, refreshLayout: SwipeRefreshLayout) {
        viewModelScope.launch {
            getInfo2().collect {
                when (it) {
                    is LatestInfoUiState.Success -> {
                        adapter.submitList(it.info)
                        d("resourceType", "success ${it.info?.size}")
                    }
                    is LatestInfoUiState.Error -> {
                        d("resourceType", "error ${it.error}")
                    }
                    is LatestInfoUiState.Loader -> {
                        refreshLayout.isRefreshing = it.isLoading
                        d("resourceType", "loader ${it.isLoading}")
                    }
                }
            }
        }
    }
}


