package rs.ac.metropolitan.projekat_cs330.view

import android.os.Build
import androidx.annotation.RequiresApi
import rs.ac.metropolitan.projekat_cs330.AppViewModel
import rs.ac.metropolitan.projekat_cs330.ui.theme.ProjekatCS330Theme

import android.graphics.drawable.Icon
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import rs.ac.metropolitan.projekat_cs330.common.Common
import rs.ac.metropolitan.projekat_cs330.common.PersonItem
import rs.ac.metropolitan.projekat_cs330.common.Persons
import java.time.LocalDate
import java.util.Date
import java.util.Stack
import java.util.UUID
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPersonScreen(vm: AppViewModel, paddingValues: PaddingValues) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var secondName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    val birthdayCalendar = rememberUseCaseState(visible = false)
    var dateOfBirth by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var selectedGender by rememberSaveable { mutableStateOf("") }
    var day by rememberSaveable { mutableStateOf(0f) }
    val genders = listOf("Male", "Female")

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()

                ) {
                    IconButton(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .scale(1.5f),
                        onClick = { vm.goBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "Nov rodjendan", style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            item {
                TextField(
                    value = firstName,
                    onValueChange = { newText ->
                        firstName = newText
                    },
                    label = { Text("First name") },
                    placeholder = { Text("Enter your first name") },
                )
            }
            item {
                TextField(
                    value = secondName,
                    onValueChange = { newText ->
                        secondName = newText
                    },
                    label = { Text("Last name") },
                    placeholder = { Text("Enter your last name") },
                )
            }
            item {
                TextField(
                    value = email,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "emailIcon"
                        )
                    },
                    onValueChange = {
                        email = it
                    },
                    label = { Text(text = "Email address") },
                    placeholder = { Text(text = "Enter your e-mail") },
                )
            }
            item {
                Button(onClick = { birthdayCalendar.show() }) {
                    Text(text = "Date of birth is: ${dateOfBirth.toString()} ")
                }
                CalendarDialog(state = birthdayCalendar,
                    config = CalendarConfig(
                        yearSelection = true,
                        monthSelection = true,
                        style = CalendarStyle.MONTH
                    ),
                    selection = CalendarSelection.Date(
                        selectedDate = LocalDate.now()
                    ) { newDate ->
                        dateOfBirth = newDate
                    })
            }
            item {
                SegmentedControl(
                    items = genders,
                    defaultSelectedItemIndex = 0,
                ) { index ->
                    selectedGender = if(index==0) "Male" else "Female"
                } }
            item {
                Text(
                    text = day.roundToInt().toString(),
                    style = MaterialTheme.typography.titleMedium,
                )
                Slider(
                    value = day,
                    onValueChange = { sliderValue_ ->
                        day = sliderValue_
                    },
                    onValueChangeFinished = {
                        // this is called when the user completed selecting the value
                    },
                    valueRange = 0f..5f,
                    steps = 4,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier.offset(y = (-8).dp)
                )
            }
            item {
                Button(onClick = { vm.submitPerson(
                    PersonItem(
                        id =  UUID.randomUUID().toString(),
                        fname = firstName.text,
                        lname= secondName.text,
                        avatar = Common.generateAvatarImage("${firstName.text} ${secondName.text}").toString(),
                        email = email.text,
                        birthdate = Date.from(dateOfBirth.atStartOfDay().toInstant(java.time.ZoneOffset.UTC)),
                        sex = selectedGender,
                        day = day.roundToInt().toString(),
                  ))
                    vm.goBack()
                }) {
                    Text(text = "Submit")
                }
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun NewStudentScreenPreview() {
    ProjekatCS330Theme {
        NewPersonScreen(AppViewModel(), PaddingValues())
    }
}