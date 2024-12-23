package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp_2.repository.RepositoryBrg
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class BarangHomeViewModel(
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    val homeUiStateBrg: StateFlow<HomeUIStateBrg> = repositoryBrg.getAllBrg()
        .filterNotNull()
        .map {
            HomeUIStateBrg(
                listBarang = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUIStateBrg(isLoading = true))
            delay(900)
        }
        .catch {
            HomeUIStateBrg(
                isLoading = false,
                isError = true,
                errorMessage = it.message ?: "Terjadi Kesalahan"
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUIStateBrg(
                isLoading = true
            )
        )
}
