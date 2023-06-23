package rs.ac.metropolitan.projekat_cs330.repository

import androidx.navigation.NavHostController
import rs.ac.metropolitan.projekat_cs330.common.PersonItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import rs.ac.metropolitan.projekat_cs330.data.ApiService
import rs.ac.metropolitan.projekat_cs330.data.RetrofitHelper

class Repository {
    var personFlow: Flow<List<PersonItem>> = flowOf(listOf())

    suspend fun loadPerson(){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.getPerson()
        if(result != null)
            personFlow = flowOf(result)
    }

    suspend fun submitPerson(personItem: PersonItem){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.addPerson(personItem)
    }
    suspend fun deletePerson(id:String){
        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val result = apiService.deletePerson(id)
    }

}