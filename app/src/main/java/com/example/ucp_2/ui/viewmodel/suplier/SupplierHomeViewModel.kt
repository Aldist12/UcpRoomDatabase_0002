package com.example.ucp_2.ui.viewmodel.suplier

import androidx.core.util.Supplier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.repository.RepositorySup
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class SupplierHomeViewModel(
    private val repositorySpl: RepositorySup
) : ViewModel() {
    val homeUiStateSpl: StateFlow<HomeUIStateSpl> = repositorySpl.getAllSupplier()
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
}data class HomeUIStateSpl(
    val listSpl: List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)