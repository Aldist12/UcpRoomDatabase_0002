package com.example.ucp_2.ui.view.barang

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