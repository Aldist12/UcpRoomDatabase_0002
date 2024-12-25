package com.example.ucp_2

import android.app.Application
import com.example.ucp_2.depedenciesinjection.ContainerApp

class StoreApp: Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}