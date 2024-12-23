package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.RepositoryBrg

class BarangViewModel(private val repositoryBrg: RepositoryBrg) : ViewModel() {
    var uiBrgState by mutableStateOf(brgUIState())

    fun updateBrgState(barangEvent: BarangEvent) {
        uiBrgState = uiBrgState.copy(barangEvent = barangEvent)
    }

    private fun validateBrgFields(): Boolean {
        val event = uiBrgState.barangEvent
        val errorBrgState = FormErrorBrgState(
            namaBarang = if (event.namaBarang.isNotEmpty()) null else "Nama Barang Tidak Boleh Kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi Tidak Boleh Kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga Tidak Boleh Kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok Tidak Boleh Kosong",
            namaSupplier = if (event.namaSupplier.isNotEmpty()) null else "Nama Supplier Tidak Boleh Kosong"
        )

        uiBrgState = uiBrgState.copy(isEntryBrgValid = errorBrgState)
        return errorBrgState.isBrgValid()
    }

    suspend fun saveDataBrg(): Boolean {
        val currentBrgEvent = uiBrgState.barangEvent

        return if (validateBrgFields()) {
            try {
                repositoryBrg.insertBrg(currentBrgEvent.toBarangEntity())
                uiBrgState = uiBrgState.copy(
                    snackBarMessage = "Data Berhasil Disimpan",
                    barangEvent = BarangEvent(),
                    isEntryBrgValid = FormErrorBrgState()
                )
                true
            } catch (e: Exception) {
                uiBrgState = uiBrgState.copy(snackBarMessage = "Data Barang Gagal Disimpan")
                false
            }
        } else {
            uiBrgState = uiBrgState.copy(snackBarMessage = "Input tidak valid. Periksa Data Kembali")
            false
        }
    }

    fun resetSnackBarBrgMessage() {
        uiBrgState = uiBrgState.copy(snackBarMessage = null)
    }
}
data class brgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryBrgValid: FormErrorBrgState = FormErrorBrgState(),
    val snackBarMessage: String? = null
)
data class FormErrorBrgState(
    val id: String? = null,
    val namaBarang: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSupplier: String? = null
) {
    fun isBrgValid(): Boolean {
        return id == null && namaBarang == null && harga == null &&
                deskripsi == null && stok == null && namaSupplier == null
    }
}
fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id.toIntOrNull() ?: 0,
    namaBarang = namaBarang,
    deskripsi = deskripsi,
    harga = harga.toIntOrNull() ?: 0,
    stok = stok.toIntOrNull() ?: 0,
    namaSupplier = namaSupplier
)
