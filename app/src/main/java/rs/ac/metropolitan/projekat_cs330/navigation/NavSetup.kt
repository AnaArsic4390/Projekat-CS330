package rs.ac.metropolitan.projekat_cs330.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import rs.ac.metropolitan.projekat_cs330.AppViewModel
import rs.ac.metropolitan.projekat_cs330.view.HomeScreen
import rs.ac.metropolitan.projekat_cs330.view.NewPersonScreen
import rs.ac.metropolitan.projekat_cs330.view.PersonDetailScreen

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavSetup(navController: NavHostController) {
    val vm: AppViewModel = viewModel()
    var paddingValues = PaddingValues()
    vm.navController = navController

    NavHost(navController = navController, startDestination = NavigationRoutes.Home.route) {
        composable(route = NavigationRoutes.Home.route) {
            HomeScreen(vm, paddingValues)
        }
        composable(route = NavigationRoutes.PersonDetailScreen.route) { navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null) {
                PersonDetailScreen(vm, elementId, paddingValues)
            }else{
                Toast.makeText(navController.context, "Error, elementId is required!", Toast.LENGTH_SHORT).show()
            }
        }
        composable(route = NavigationRoutes.NewPerson.route) {
            NewPersonScreen(vm, paddingValues)
        }
    }
}