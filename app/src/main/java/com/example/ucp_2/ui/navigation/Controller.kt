package com.example.ucp_2.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp_2.ui.view.barang.UpdateBarangView
import com.example.ucp_2.ui.view.Homepage
import com.example.ucp_2.ui.view.barang.DetailBrgView
import com.example.ucp_2.ui.view.barang.HomeBrgView
import com.example.ucp_2.ui.view.barang.InsertBrgView
import com.example.ucp_2.ui.view.suplier.HomeSupView
import com.example.ucp_2.ui.view.suplier.InsertSupView

@Composable
fun MainControllerPage(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            Homepage(
                onItemClick = { item ->
                    when (item) {
                        "Supplier" -> navController.navigate(DestinasiHomeSpl.route)
                        "Barang" -> navController.navigate(DestinasiHomeBrg.route)
                        else -> {}
                    }
                }
            )
        }
        composable(
            route = DestinasiHomeSpl.route
        ) {
            HomeSupView(
                onAddSplClick = {
                    navController.navigate(DestinasiInsertSpl.route)
                },
                onBackArrow = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = DestinasiInsertSpl.route
        ) {
            InsertSupView(
                onBackArrow = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }
        composable(
            route = DestinasiHomeBrg.route
        ) {
            HomeBrgView(
                onAddBrgClick = {
                    navController.navigate(DestinasiInsertBrg.route)
                },
                onDetailBrgClick = { idBrg ->
                    navController.navigate("${DestinasiDetailBrg.route}/$idBrg")
                },
                onBackArrow = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = DestinasiInsertBrg.route
        ) {
            InsertBrgView(
                onBackArrow = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }
        composable(
            DestinasiDetailBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.idBrg) {
                    type = NavType.StringType
                }
            )
        ) {
            val idBarang = it.arguments?.getString(DestinasiDetailBrg.idBrg)
            idBarang?.let { idBarang ->
                DetailBrgView(
                    onBackArrow = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBrg.route}/$it")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.idBrg) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateBarangView(
                onBackArrow = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                }
            )
        }
    }
}