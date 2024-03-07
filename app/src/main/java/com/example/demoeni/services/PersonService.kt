package com.example.demoeni.services

import com.example.demoeni.viewmodel.Person
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface PersonService {

    companion object{
        val BASE_URL = "http://127.0.0.1:3000/";

        //Outil to convert Kotlin to Json
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build();

        //Retrofit : API calls : send requests
        val retroFit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build();

    }

    @GET("persons")
    suspend fun getPersons() : List<Person>



    object PersonApi {
        val retrofitService : PersonService by lazy { retroFit.create(PersonService::class.java) }
    }

}
