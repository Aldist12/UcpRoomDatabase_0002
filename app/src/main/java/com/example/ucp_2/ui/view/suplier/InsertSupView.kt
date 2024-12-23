package com.example.ucp_2.ui.view.suplier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun InsertSupView(
    onBackArrow: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SupplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiSplState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarSplMessage()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Tambah Data Supplier",
                onBackClick = onBackArrow,
                actionIcon = R.drawable.supplier
            )
        },
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {


            InsertBodySup(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateSplState(updatedEvent)
                },
                onClick = {
                    val isSaved = viewModel.saveDataSupplier()
                    if (isSaved) {
                        onNavigate()
                    }
                }
            )
        }
    }
}
@Composable
fun InsertBodySup(
    modifier: Modifier = Modifier,
    onValueChange: (SupplierEvent) -> Unit,
    uiState: splUIState,
    onClick: suspend () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Form Tambah Supplier",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start)
        )

        FormSupplier(
            supplierEvent = uiState.supplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntrySplValid,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    onClick()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Simpan",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
