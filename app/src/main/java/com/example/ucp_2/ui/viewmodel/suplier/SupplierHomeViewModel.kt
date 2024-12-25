package com.example.ucp_2.ui.viewmodel.suplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.suplier.RepositorySup
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SupplierHomeViewModel(
    private val repositorySup: RepositorySup
) : ViewModel() {
    val homeUiStateSpl: StateFlow<HomeUIStateSpl> = repositorySup.getAllSuplier()
        .filterNotNull()
        .map {
            HomeUIStateSpl(
                listSpl = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUIStateSpl(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUIStateSpl(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUIStateSpl(
                isLoading = true
            )
        )
}

data class HomeUIStateSpl(
    val listSpl: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)