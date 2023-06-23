package rs.ac.metropolitan.projekat_cs330


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.projekat_cs330.navigation.NavSetup
import rs.ac.metropolitan.projekat_cs330.navigation.NavigationRoutes
import rs.ac.metropolitan.projekat_cs330.ui.theme.ProjekatCS330Theme
import rs.ac.metropolitan.projekat_cs330.view.HomeScreen

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjekatCS330Theme {
                // A surface container using the 'background' color from the theme
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                NavSetup(navController = navController)



                

            }
        }
    }
}
