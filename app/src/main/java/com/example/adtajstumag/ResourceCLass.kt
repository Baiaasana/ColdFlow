package com.example.adtajstumag

sealed class LatestInfoUiState<out T>(open val isLoading: Boolean) {
    data class Success<out T>(val info: T?, override val isLoading: Boolean = false): LatestInfoUiState<T>(false)
    data class Error(val error: String, override val isLoading: Boolean = false): LatestInfoUiState<Nothing>(false)
    class Loader(override val isLoading: Boolean = true) : LatestInfoUiState<Nothing>(isLoading)
}
