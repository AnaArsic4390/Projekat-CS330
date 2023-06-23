package rs.ac.metropolitan.projekat_cs330.view

import android.os.Build
import androidx.annotation.RequiresApi
import rs.ac.metropolitan.projekat_cs330.AppViewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import rs.ac.metropolitan.projekat_cs330.common.Common
import rs.ac.metropolitan.projekat_cs330.common.PersonItem
import rs.ac.metropolitan.projekat_cs330.ui.theme.ProjekatCS330Theme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonDetailScreen(vm: AppViewModel, elementId: String, paddingValues: PaddingValues) {
    PersonBasicData(person = vm.getPerson(elementId), goBack = { vm.goBack() }, delete = { vm.deletePerson(elementId) }, )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PersonBasicData(person: PersonItem?, goBack: () -> Unit, delete: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                modifier = Modifier
                    .background(Color.Transparent)
                    .scale(1.5f),
                onClick = { goBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(text = "Detanji o ${person?.fname} ", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.Center))

            IconButton(
                modifier = Modifier
                    .scale(1.5f)
                    .align(Alignment.BottomEnd),
                onClick = { delete() }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
        person?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                AsyncImage(
                    model = it.avatar,
                    contentDescription = null,
                    modifier = Modifier
                        .size(240.dp)

                )
                Text(
                    text = "${it.fname} ${it.lname}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = "Email: ${person.email}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 8.dp)
                    ) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Person")
                        Text(
                            text = "${person.sex}",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${Common.dateToString(person.birthdate)}",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PersonDetailScreenPreview() {
    ProjekatCS330Theme {
        PersonDetailScreen(AppViewModel(), "", PaddingValues())
    }
}