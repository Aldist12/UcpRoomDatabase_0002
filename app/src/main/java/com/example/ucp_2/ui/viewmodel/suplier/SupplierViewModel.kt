package com.example.ucp_2.ui.viewmodel.suplier

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ucp_2.repository.RepositorySup

class SupplierViewModel(private val repositorySpl: RepositorySup) : ViewModel() {
    var uiSplState by mutableStateOf(splUIState())

    fun updateSplState(supplierEvent: SupplierEvent) {
        uiSplState = uiSplState.copy(supplierEvent = supplierEvent)
    }