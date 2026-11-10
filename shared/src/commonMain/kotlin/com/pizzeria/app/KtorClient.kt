package com.pizzeria.app

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    }


    //private const val BASE_URL = "http://10.0.2.2:8080"
    private const val BASE_URL = "http://pizzeria-backend-production-4a51.up.railway.app"
    suspend fun fetchProducts(): List<Product> {
        return try {
            client.get("$BASE_URL/products").body()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun login(request: LoginRequest): LoginResponse {
        return try {
            client.post("$BASE_URL/login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            LoginResponse(success = false, message = "Error de conexión con el servidor")
        }
    }
}