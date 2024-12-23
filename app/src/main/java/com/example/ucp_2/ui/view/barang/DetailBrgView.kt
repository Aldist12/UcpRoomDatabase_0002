package com.example.ucp_2.ui.view.barang

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.R
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.ui.costumwidget.TopAppBar
import java.lang.reflect.Modifier

@Composable
fun DetailBrgView(
    viewModel: DetailBarangViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBackArrow: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = "Detail Data Barang",
                onBackClick = onBackArrow,
                actionIcon = R.drawable.box
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailBrgUiState.value.detailUiBrgEvent.id)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = androidx.compose.ui.Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
        }
    ) { innerPadding ->
        val detailBrgUiState by viewModel.detailBrgUiState.collectAsState()

        BarangDetailBody(
            modifier = Modifier.padding(innerPadding),
            detailBrgUiState = detailBrgUiState,
            onDeleteClick = {
                viewModel.deleteBrg()
                onDeleteClick()
            }
        )
    }
}

@Composable
fun BarangDetailBody(
    modifier: Modifier = Modifier,
    detailBrgUiState: DetailBrgUiState,
    onDeleteClick: () -> Unit = { }
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailBrgUiState.isLoading -> {
            LoadingState()
        }

        detailBrgUiState.isUiBarangEventNotEmpty -> {
            Column(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ItemDetailBrg(
                    barang = detailBrgUiState.detailUiBrgEvent.toBarangEntity(),
                    modifier = modifier
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                    Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
                    Text("Delete", color = Color.White)
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = androidx.compose.ui.Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailBrgUiState.isUiBarangEmpty -> {
            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak Ditemukan",
                    modifier = androidx.compose.ui.Modifier.padding(16.dp)
                )
            }
        }
    }
}
@Composable
fun ItemDetailBrg(
    modifier: Modifier = Modifier,
    barang: Barang
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier.padding(16.dp)
        ) {
            // ID
            ComponentDetailBrg(
                title = "ID",
                content = barang.id.toString()
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "ID Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = androidx.compose.ui.Modifier.size(24.dp)
                )
            }

            HorizontalDivider(modifier = androidx.compose.ui.Modifier.padding(vertical = 8.dp))

            // Nama Barang
            ComponentDetailBrg(
                title = "Nama Barang",
                content = barang.namaBarang
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.item),
                    contentDescription = "Nama Barang Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = androidx.compose.ui.Modifier.size(24.dp)
                )
            }

            HorizontalDivider(modifier = androidx.compose.ui.Modifier.padding(vertical = 8.dp))

            // Deskripsi
            ComponentDetailBrg(
                title = "Deskripsi",
                content = barang.deskripsi
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Deskripsi Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = androidx.compose.ui.Modifier.size(24.dp)
                )
            }

            HorizontalDivider(modifier = androidx.compose.ui.Modifier.padding(vertical = 8.dp))

            // Harga
            ComponentDetailBrg(
                title = "Harga",
                content = "Rp${barang.harga}"
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.money),
                    contentDescription = "Harga Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = androidx.compose.ui.Modifier.size(24.dp)
                )
            }

            HorizontalDivider(modifier = androidx.compose.ui.Modifier.padding(vertical = 8.dp))

            // Stok
            ComponentDetailBrg(
                title = "Stok",
                content = barang.stok.toString()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.stock),
                    contentDescription = "Stok Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = androidx.compose.ui.Modifier.size(24.dp)
                )
            }

            HorizontalDivider(modifier = androidx.compose.ui.Modifier.padding(vertical = 8.dp))

            // Nama Supplier
            ComponentDetailBrg(
                title = "Nama Supplier",
                content = barang.namaSupplier
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Nama Supplier Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = androidx.compose.ui.Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data barang ini?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Yes")
            }
        }
    )
}
@Composable
fun ComponentDetailBrg(
    modifier: Modifier = androidx.compose.ui.Modifier,
    title: String,
    content: String,
    iconContent: @Composable () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconContent()
        Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
        Column {
            Text(
                text = "$title:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = content,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

