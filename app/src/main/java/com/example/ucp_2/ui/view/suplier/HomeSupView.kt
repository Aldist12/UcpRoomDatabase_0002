package com.example.ucp_2.ui.view.suplier

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.R
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.ui.costumwidget.TopAppBar

@Composable
fun HomeSupView(
    modifier: Modifier = Modifier,
    viewModel: SupplierHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddSplClick: () -> Unit = {},
    onBackArrow: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Data Supplier",
                onBackClick = onBackArrow,
                actionIcon = R.drawable.supplier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSplClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Supplier"
                )
            }
        }
    ) { innerPadding ->
        val homeSplUiState by viewModel.homeUiStateSpl.collectAsState()

        BodyHomeSupView(
            homeUiState = homeSplUiState,
            modifier = modifier.padding(innerPadding)
        )
    }
}
