package rs.ac.metropolitan.projekat_cs330.view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import rs.ac.metropolitan.projekat_cs330.AppViewModel
import rs.ac.metropolitan.projekat_cs330.common.PersonItem
import rs.ac.metropolitan.projekat_cs330.ui.theme.ProjekatCS330Theme
@Composable
fun PersonListPage(vm: AppViewModel, paddingValues: PaddingValues) {
    val person = vm.person.observeAsState()
    LaunchedEffect(vm.loadPerson()) {
    }

    val colors = listOf(Color(0xFFcad594), Color(0xFFffd7c9), Color(0xFFffd88c))

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        person.value?.let { persons ->
            itemsIndexed(persons) { index, person ->
                if (index % 2 == 0) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().size(150.dp).padding(vertical = 0.dp)

                    ) {
                        val color1 = colors[(index / 2) % colors.size]
                        val color2 = colors[((index / 2) + 1) % colors.size]

                        PersonCardView(person, color1) {
                            vm.navigateToUserDetails(it)
                        }

                        if (index + 1 < persons.size) {
                            PersonCardView(persons[index + 1], color2) {
                                vm.navigateToUserDetails(it)
                            }
                        } else {
                            Spacer(modifier = Modifier.width(120.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PersonCardView(person: PersonItem, color: Color, onSelected: (String) -> Unit) {

    val showBirthDate = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(0.dp)
            .size(200.dp)
            .clickable {
                showBirthDate.value = !showBirthDate.value
                onSelected(person.id)
            },
        contentAlignment = Alignment.Center,
        content = {
            Box(
                modifier = Modifier
                    .background(color)
                    .fillMaxSize()
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "${person.fname}",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            if (showBirthDate.value) {
                Text(
                    text = "Datum rođenja: ${person.birthdate}",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        }
    )
}

@Preview
@Composable
fun ListPersonViewPreview() {
    ProjekatCS330Theme {
        PersonListPage(AppViewModel(), PaddingValues())
    }
}
