package com.example.ucp_2.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.suplier.RepositorySup

class SupplierViewModel(private val repositorySup: RepositorySup) : ViewModel() {
    var uiSplState by mutableStateOf(splUIState())

    fun updateSplState(supplierEvent: SupplierEvent) {
        uiSplState = uiSplState.copy(supplierEvent = supplierEvent)
    }
    private fun validateSplFields(): Boolean {
        val event = uiSplState.supplierEvent
        val errorSplState = FormErrorSplState(
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak Tidak Boleh Kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat Tidak Boleh Kosong",
        )

        uiSplState = uiSplState.copy(isEntrySplValid = errorSplState)
        return errorSplState.isSplValid()
    }
    suspend fun saveDataSupplier(): Boolean {
        val currentSplEvent = uiSplState.supplierEvent

        return if (validateSplFields()) {
            try {
                repositorySup.insertSuplier(currentSplEvent.toSupplierEntity())
                uiSplState = uiSplState.copy(
                    snackBarMessage = "Data Berhasil Disimpan",
                    supplierEvent = SupplierEvent(),
                    isEntrySplValid = FormErrorSplState()
                )
                true
            } catch (e: Exception) {
                uiSplState = uiSplState.copy(snackBarMessage = "Data Supplier Gagal Disimpan")
                false
            }
        } else {
            uiSplState = uiSplState.copy(snackBarMessage = "Input tidak valid. Periksa Data Kembali")
            false
        }
    }
    fun resetSnackBarSplMessage() {
        uiSplState = uiSplState.copy(snackBarMessage = null)
    }
}
data class splUIState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntrySplValid: FormErrorSplState = FormErrorSplState(),
    val snackBarMessage: String? = null
)

data class FormErrorSplState(
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isSplValid(): Boolean {
        return nama == null &&
                kontak == null && alamat == null
    }
}
fun SupplierEvent.toSupplierEntity(): Suplier = Suplier(
    id = id,
    nama = nama,
    kontak = kontak,
    alamat = alamat
)

data class SupplierEvent(
    val id: Int = 0,
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = ""
)