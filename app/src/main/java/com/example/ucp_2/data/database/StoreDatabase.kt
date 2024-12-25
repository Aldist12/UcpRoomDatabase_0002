package com.example.ucp_2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.data.dao.BarangDao
import com.example.ucp_2.data.dao.SuplierDao

@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplierDao

    companion object {
        @Volatile
        private var Instance: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    StoreDatabase::class.java,
                    "Store Database"
                ).build().also { Instance = it }
            }
        }
    }
}