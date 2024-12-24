package com.example.ucp_2.ui.viewmodel.suplier

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ucp_2.repository.RepositorySup

class SupplierViewModel(private val repositorySpl: RepositorySup) : ViewModel() {
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