package com.example.ucp_2.ui.navigation

import com.example.ucp_2.ui.navigation.DestinasiUpdate.NAMA

interface Alamatnavigasi {
    val route: String
}

object DestinasiHome : Alamatnavigasi {
    override val route = "home"
}

object DestinasiDetail : Alamatnavigasi {
    override val route = "detail"
    const val NIM = "nama"
    val routesWithArg = "$route/{$NAMA}"
}

object DestinasiUpdate : Alamatnavigasi {
    override val route = "update"
    const val NAMA = "nama"
    val routesWithArg = "$route/{$NAMA}"
}