package com.example.ucp_2.ui.costumwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ucp_2.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    actionIcon: Int,
    onActionClick: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.primary),
            titleContentColor = Color.White
        ),
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate Back",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Image(
                    painter = painterResource(id = actionIcon),
                    contentDescription = "Action Icon"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopAppBar() {
    TopAppBar(
        title = "Manage Barang",
        onBackClick = { },
        actionIcon = R.drawable.box,
        onActionClick = { }
    )
}