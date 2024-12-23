package com.example.ucp_2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Homepage(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        HeaderSection()
        BodySection(onItemClick = onItemClick)
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 48.dp))
            .background(color = colorResource(R.color.primary))
            .padding(bottom = 32.dp)
            .padding(top = 20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 24.dp)
        ) {
            Column {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home Icon",
                    tint = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(Modifier.padding(3.dp))
                Text(
                    text = "Discover the Power Behind the Products",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Welcome to Our Warehouse!",
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Box(
            Modifier.align(Alignment.CenterEnd)
                .padding(24.dp)
                .padding(top = 12.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.warehouse),
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun BodySection(
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Selamat datang di aplikasi manajemen gudang andalan Anda!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Gunakan berbagai fitur kami untuk mengelola Supplier dan Barang dengan lebih mudah dan efisien. " +
                    "Pastikan Anda selalu memantau stok agar operasional berjalan lancar tanpa hambatan.",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ManageBox(
            title = "Manage Supplier",
            description = "Kelola data supplier dengan mudah di sini. Pantau dan atur informasi supplier dengan cepat dan efisien.",
            backgroundColor = Color(0xFF00C853),
            iconResource = R.drawable.supplier,
            onClick = { onItemClick("Supplier") }
        )

        ManageBox(
            title = "Manage Barang",
            description = "Kelola data barang dengan praktis di sini. Tambahkan, ubah, atau hapus data barang sesuai kebutuhan Anda.",
            backgroundColor = Color(0xFF007BFF),
            iconResource = R.drawable.box,
            onClick = { onItemClick("Barang") }
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = "Optimalkan performa gudang Anda dengan terus memperbarui data barang dan supplier. Aplikasi ini hadir untuk mendukung kebutuhan operasional Anda!",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}