package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


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
data class HomeUIStateBrg(
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
