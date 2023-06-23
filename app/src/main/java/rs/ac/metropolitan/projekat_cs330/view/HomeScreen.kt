package rs.ac.metropolitan.projekat_cs330.view


import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import rs.ac.metropolitan.projekat_cs330.AppViewModel
import rs.ac.metropolitan.projekat_cs330.ui.theme.ProjekatCS330Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(vm: AppViewModel = viewModel(), paddingValues: PaddingValues) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        vm.granted.value = isGranted
    }

    Column {
        if (!vm.granted.value) {
            InternetPermission(launcher)
        } else {
            Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Rodjendani",
                            color = Color(0xFFcad594)
                        )
                    }
                )
                              },
                floatingActionButton = {
                    FloatingActionButton(onClick = { vm.navigateToNewPerson() }) {
                        Icon(Icons.Filled.Add, contentDescription = "Dodaj rodjendan")
                    }
                }) { innerPadding ->
                PersonListPage(vm, innerPadding)
            }

        }
    }
}

@Composable
private fun InternetPermission(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 16.dp)) {
            Text(
                text = "Nezaboravi poklon",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = { launcher.launch(Manifest.permission.INTERNET) }
            ) {
                Text("Priseti se sad ->")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ProjekatCS330Theme() {
        HomeScreen(AppViewModel(), PaddingValues(8.dp))
    }
}