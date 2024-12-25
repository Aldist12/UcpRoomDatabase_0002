package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.barang.RepositoryBrg
import com.example.ucp_2.ui.navigation.DestinasiDetailBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
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
fun Barang.toDetailBrglUiEvent() : BarangEvent {
    return BarangEvent(
        id = id.toString(),
        namaBarang = namaBarang,
        deskripsi = deskripsi,
        harga = harga.toString(),
        stok = stok.toString(),
        namaSupplier = namaSuplier
    )
}