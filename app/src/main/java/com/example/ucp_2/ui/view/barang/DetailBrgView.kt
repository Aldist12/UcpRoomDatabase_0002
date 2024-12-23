package com.example.ucp_2.ui.view.barang

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
                modifier = Modifier.padding(16.dp)
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
