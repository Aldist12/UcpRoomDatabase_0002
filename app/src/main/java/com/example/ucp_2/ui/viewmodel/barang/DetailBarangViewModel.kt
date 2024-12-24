package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailBarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {
    private val _id: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.idBrg])

    val detailBrgUiState: StateFlow<DetailBrgUiState> = repositoryBrg.getBarang(_id.toInt())
        .filterNotNull()
        .map {
            DetailBrgUiState(
                detailUiBrgEvent = it.toDetailBrglUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailBrgUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailBrgUiState(
                    isLoading = false,
                    isError = true,
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailBrgUiState(
                isLoading = true
            )
        )
    fun deleteBrg(){
        detailBrgUiState.value.detailUiBrgEvent.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBrg.deleteBrg(it)
            }
        }
    }
}
data class DetailBrgUiState(
    val detailUiBrgEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorBrgMessage: String = ""
) {
    val isUiBarangEmpty: Boolean
        get() = detailUiBrgEvent == BarangEvent()

    val isUiBarangEventNotEmpty:Boolean
        get() = detailUiBrgEvent != BarangEvent()
}
