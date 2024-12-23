package com.example.ucp_2.ui.view.suplier

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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