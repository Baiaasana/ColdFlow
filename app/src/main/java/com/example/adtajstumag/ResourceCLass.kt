package com.example.adtajstumag

sealed class LatestInfoUiState<out T> {
    data class Success<out T>(val info: T?): LatestInfoUiState<T>()
    data class Error(val error: String): LatestInfoUiState<Nothing>()
    class Loader( val isLoading: Boolean) : LatestInfoUiState<Nothing>()
}
