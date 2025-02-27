package ies.sequeros.repository

import io.ktor.serialization.gson.*

import com.google.gson.JsonParser
import ies.sequeros.model.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.serialization.gson.gson
class PokemonRepository {

    val client = HttpClient() {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting() // ✅ Formatea JSON legible
                serializeNulls()
            }
        }
    }
    suspend fun login(user:String,password:String): Boolean {
        val response: HttpResponse =
            client.get("https://digi-api.com/api/v1/digimon/${user}") {
                header("Accept", "application/json")
            }
        if(response.status== HttpStatusCode.OK ) {
            return true
        }
        else{
            return false
        }
    }

    suspend fun getAll(): List<Pokemon> {
        val items = mutableListOf<Pokemon>()
        val response: HttpResponse =
            client.get("https://digi-api.com/api/v1/digimon?pageSize=50") {
                header("Accept", "application/json")
            }
        var json = JsonParser.parseString(response.bodyAsText()).asJsonObject
        json["content"].asJsonArray.forEach {
            var item = Pokemon(
                it.asJsonObject["id"].asInt,
                it.asJsonObject["name"].asString,
                it.asJsonObject["href"].asString,
                it.asJsonObject["image"].asString
            )
            items.add(item)
        }
        return items

    }
    suspend fun getById(id:Int):Pokemon{
        val response: HttpResponse =
            client.get("https://digi-api.com/api/v1/digimon/${id}") {
                header("Accept", "application/json")
            }
        var json = JsonParser.parseString(response.bodyAsText()).asJsonObject
            var item = Pokemon(
                json.asJsonObject["id"].asInt,
                json.asJsonObject["name"].asString,
                json.asJsonObject["href"].asString,
                json.asJsonObject["image"].asString
            )
        return item
    }
}