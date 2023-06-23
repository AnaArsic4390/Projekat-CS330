package rs.ac.metropolitan.projekat_cs330

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rs.ac.metropolitan.projekat_cs330.common.PersonItem
import rs.ac.metropolitan.projekat_cs330.navigation.NavigationRoutes
import rs.ac.metropolitan.projekat_cs330.repository.Repository

class AppViewModel : ViewModel() {
    lateinit var navController: NavHostController
    val repository = Repository()
    var granted = mutableStateOf(false)

    private val _persons = MutableLiveData<List<PersonItem>>()
    val person: LiveData<List<PersonItem>>
        get() = _persons

    // Komunikacija sa repozitorijumom
    fun loadPerson() {
        GlobalScope.launch {
            repository.loadPerson()
            MainScope().launch {
                repository.personFlow.collect {
                    _persons.value = it
                }
            }
        }
    }

    fun getPerson(id: String): PersonItem? {
        return _persons.value?.find { it.id == id }
    }
    fun deletePerson(id: String) {
        GlobalScope.launch {
            repository.deletePerson(id)
        }
        goBack()
    }

    fun submitPerson(person: PersonItem) {
        GlobalScope.launch {
            repository.submitPerson(person)
        }
    }

    // Routing methods
    fun navigateToUserDetails(id: String) {
        navController.navigate(NavigationRoutes.PersonDetailScreen.createRoute(id))
    }

    fun navigateToNewPerson() {
        navController.navigate(NavigationRoutes.NewPerson.route)
    }

    fun goBack() {
        navController.popBackStack()
    }
}