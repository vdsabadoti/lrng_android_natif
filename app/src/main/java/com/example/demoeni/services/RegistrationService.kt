package com.example.demoeni.services

import com.example.demoeni.viewmodel.BusinessResponse
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.Person
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface RegistrationService {

    companion object{
        val BASE_URL = "http://127.0.0.1:3000/";

        //Outil to convert Kotlin to Json
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build();

        //Retrofit : API calls : send requests
        val retroFit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build();

    }

    @POST("signup")
    suspend fun registration(@Body person: Person) : BusinessResponse<Any>


    object RegistrationApi {
        val retrofitService : RegistrationService by lazy { retroFit.create(RegistrationService::class.java) }
    }

}
