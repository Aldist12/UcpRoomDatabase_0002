package com.example.ucp_2


class StoreApp {
    lateinit var containerApp: ContainerApp // Fungsi untuk menyimpan

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) // membuat instance
        // instance adalah object yanng dibuat dari class
    }
}
}