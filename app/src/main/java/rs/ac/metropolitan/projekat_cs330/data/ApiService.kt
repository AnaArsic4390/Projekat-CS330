package rs.ac.metropolitan.projekat_cs330.data


import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rs.ac.metropolitan.projekat_cs330.common.PersonItem
interface ApiService {

    @GET(Constants.PERSON_URL)
    suspend fun getPerson(): List<PersonItem>

    @POST(Constants.PERSON_URL)
    suspend fun addPerson(@Body personItem: PersonItem)

    @DELETE(Constants.PERSON_URL+"/{id}")
    suspend fun deletePerson(@Path("id") id:String)
}