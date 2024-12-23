package com.example.ucp_2.ui.view.barang

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.R
import com.example.ucp_2.ui.costumwidget.TopAppBar
import kotlinx.coroutines.launch

@Composable
fun InsertBrgView(
    onBackArrow: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiBrgState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val title = "Tambah Data Barang"

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarBrgMessage()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                onBackClick = onBackArrow,
                actionIcon = R.drawable.box
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
            InsertBodyBrg(
                uiState = uiState,
                formTitle = "Form $title",
                onValueChange = { updatedEvent ->
                    viewModel.updateBrgState(updatedEvent)
                },
                onClick = {
                    val isSaved = viewModel.saveDataBrg()
                    if (isSaved) {
                        onNavigate()
                    }
                }
            )
        }
    }
}

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    formTitle: String,
    onValueChange: (BarangEvent) -> Unit,
    uiState: brgUIState,
    onClick: suspend () -> Unit,
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
            text = formTitle,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Start)
        )
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorBrgState = uiState.isEntryBrgValid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun FormBarang(
    modifier: Modifier = Modifier,
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = { },
    errorBrgState: FormErrorBrgState = FormErrorBrgState()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Nama Barang
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.namaBarang,
            onValueChange = {
                onValueChange(barangEvent.copy(namaBarang = it))
            },
            label = { Text("Nama Barang") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.item),
                    contentDescription = "Nama Barang Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = { Text("Masukkan Nama Barang") },
            isError = errorBrgState.namaBarang != null,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorBorderColor = Color.Red
            )
        )
        if (errorBrgState.namaBarang != null) {
            Text(
                text = errorBrgState.namaBarang,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Deskripsi
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "Deskripsi Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
            placeholder = { Text("Masukkan Deskripsi Barang") },
            isError = errorBrgState.deskripsi != null,
            singleLine = false,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorBorderColor = Color.Red
            )
        )
        if (errorBrgState.deskripsi != null) {
            Text(
                text = errorBrgState.deskripsi,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }

        // Harga
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                val filteredInput = it.filter { char -> char.isDigit() }
                onValueChange(barangEvent.copy(harga = filteredInput))
            },
            label = { Text("Harga") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.money),
                    contentDescription = "Harga Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = { Text("Masukkan Harga") },
            isError = errorBrgState.harga != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorBorderColor = Color.Red
            )
        )
        if (errorBrgState.harga != null) {
            Text(
                text = errorBrgState.harga,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }

        // Stok
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                val filteredInput = it.filter { char -> char.isDigit() }
                onValueChange(barangEvent.copy(stok = filteredInput))
            },
            label = { Text("Stok") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.stock),
                    contentDescription = "Stok Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            },
            placeholder = { Text("Masukkan Stok Barang") },
            isError = errorBrgState.stok != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorBorderColor = Color.Red
            )
        )
        if (errorBrgState.stok != null) {
            Text(
                text = errorBrgState.stok,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }

        // Supplier
        DropDownSupplier(
            selectedValue = barangEvent.namaSupplier,
            options = NamaSupplier.options(),
            label = "Nama Supplier",
            onValueChangedEvent = { selectedSupplier ->
                onValueChange(barangEvent.copy(namaSupplier = selectedSupplier))
            }
        )
        if (errorBrgState.namaSupplier != null) {
            Text(
                text = errorBrgState.namaSupplier,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}