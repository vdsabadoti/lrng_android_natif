package com.example.demoeni.services

import com.example.demoeni.viewmodel.BusinessResponse
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.Person
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface LoginService {

    companion object{
        val BASE_URL = "http://127.0.0.1:3000/";

        //Outil to convert Kotlin to Json
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build();

        val okHttpClient = OkHttpClient.Builder()
            //.addInterceptor(AuthInterceptor)
            .build()

        //Retrofit : API calls : send requests
        val retroFit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build();

    }

    //If successfull, returns a string with a token
    @POST("login")
    suspend fun login(@Body person : Person) : BusinessResponse<Any>

    @GET("verify-token")
    suspend fun verifyToken(@Header("authorization") token : String) : BusinessResponse<Any>


    object LoginApi {
        val retrofitService : LoginService by lazy { retroFit.create(LoginService::class.java) }
    }

}
