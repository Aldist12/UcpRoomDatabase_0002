package com.example.ucp_2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblBarang")
data class Barang(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val namaBarang: String,
    val deskripsi: String,
    val harga: Int,
    val stok: Int,
    val namaSuplier: String
)
