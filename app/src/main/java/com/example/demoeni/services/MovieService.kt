package com.example.demoeni.services

import com.example.demoeni.viewmodel.Film
import com.example.demoeni.viewmodel.Person
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    companion object{
        val BASE_URL = "http://127.0.0.1:3000/";

        //Outil to convert Kotlin to Json
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build();

        //Retrofit : API calls : send requests
        val retroFit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build();

    }

    @GET("movies")
    suspend fun getMovies() : List<Film>

    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id : Int) : Film

    @POST("movie/edit/{id}")
    suspend fun editMovieById(@Path("id") id : Int?, @Body film : Film?)

    @POST("movie/create")
    suspend fun editMovieById( @Body film : Film?)

    @DELETE("movie/delete/{id}")
    suspend fun delete(@Path("id") id: Int?)


    object MovieApi {
        val retrofitService : MovieService by lazy { retroFit.create(MovieService::class.java) }
    }

}
