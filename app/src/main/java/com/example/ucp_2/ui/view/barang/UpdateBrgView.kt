package com.example.ucp_2.ui.view.barang

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.R
import com.example.ucp_2.ui.costumwidget.TopAppBar
import com.example.ucp_2.ui.viewmodel.PenyediaViewModel
import com.example.ucp_2.ui.viewmodel.barang.UpdateBarangViewModel

@Composable
fun UpdateBarangView(
    onBackArrow: () -> Unit,
    onNavigate : () -> Unit,
    viewModel: UpdateBarangViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.updateBrgUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val title = "Edit Data Barang"
    LaunchedEffect (uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long
            )
            viewModel.resetSnackBarMessage()
        }
    }
    Scaffold (
        topBar = {
            TopAppBar(
                title = title,
                onBackClick = onBackArrow,
                actionIcon = R.drawable.box
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            InsertBodyBrg(
                uiState = uiState,
                formTitle = "Form $title",
                onValueChange = { updatedEvent ->
                    viewModel.UpdateStateBrg(updatedEvent)
                },
                onClick = {
                    val isSaved = viewModel.updateDataBrg()
                    if (isSaved) {
                        onNavigate()
                    }
                }
            )
        }
    }
}