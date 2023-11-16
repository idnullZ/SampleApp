package kz.zunun.myapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://jsonplaceholder.typicode.com/todos

fun Retrofit(options: Retrofit.Builder.() -> Unit): Retrofit {
    return Retrofit.Builder().apply(options).build()
}

fun OkHttp(options: OkHttpClient.Builder.() -> Unit = { }): OkHttpClient {
    return OkHttpClient.Builder().apply(options).build()
}


fun createApi(): Api {

    val retrofit = Retrofit {
        baseUrl("https://jsonplaceholder.typicode.com")
        addConverterFactory(GsonConverterFactory.create())
        client(
            OkHttp {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
            }
        )
    }


    return retrofit.create(Api::class.java)
}

interface Api {

    @GET("todos")
    suspend fun getAll(): List<RespondItem>
}