package com.example.ucp_2.ui.view.barang

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.R
import com.example.ucp_2.ui.costumwidget.TopAppBar

@Composable
fun HomeBrgView(
    viewModel: BarangHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddBrgClick: () -> Unit = { },
    onDetailBrgClick: (String) -> Unit = { },
    onBackArrow: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Data Barang",
                onBackClick = onBackArrow,
                actionIcon = R.drawable.box
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBrgClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Barang"
                )
            }
        }
    ) { innerPadding ->
        val homeBrgUiState by viewModel.homeUiStateBrg.collectAsState()

        BodyHomeBrgView(
            homeUiState = homeBrgUiState,
            onClick = {
                onDetailBrgClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeBrgView (
    homeUiState : HomeUIStateBrg,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading -> {
            LoadingState()
        }

        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let{ message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiState.listBarang.isEmpty() -> {
            Box (
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text (
                    text = "Tidak ada data Barang. ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListBarang(
                listBrg = homeUiState.listBarang,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}
