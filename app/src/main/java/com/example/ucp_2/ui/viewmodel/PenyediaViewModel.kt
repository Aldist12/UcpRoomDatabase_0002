package com.example.ucp_2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp_2.ui.viewmodel.barang.*
import com.example.ucp_2.ui.viewmodel.suplier.SupplierHomeViewModel
import com.example.ucp_2.ui.viewmodel.suplier.SupplierViewModel
import com.example.ucp_2.StoreApp

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            SupplierViewModel(
                StoreApp().containerApp.repositorySup
            )
        }
        initializer {
            SupplierHomeViewModel(
                StoreApp().containerApp.repositorySup
            )
        }
        initializer {
            BarangViewModel(
                StoreApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            BarangHomeViewModel(
                StoreApp().containerApp.repositoryBrg
            )
        }
        initializer {
            DetailBarangViewModel(
                createSavedStateHandle(),
                StoreApp().containerApp.repositoryBrg
            )
        }
        initializer {
            UpdateBarangViewModel(
                createSavedStateHandle(),
                StoreApp().containerApp.repositoryBrg,
            )
        }
    }
}
fun CreationExtras.StoreApp(): StoreApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as StoreApp)