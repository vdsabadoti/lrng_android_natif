package com.example.demoeni.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.utils.Helpers
import androidx.lifecycle.viewModelScope
import com.example.demoeni.MoviesListActivity
import com.example.demoeni.services.LoginService
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MovieRegisterViewModel(var movie: Film = Film(10)) :
    ViewModel() {

    fun create(context: Context){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = MovieService.MovieApi.retrofitService.editMovieById(User.getInstance()?.getValidToken(), movie);
            Helpers.closeProgressDialog()
            if (response.code == "202") {
                Helpers.showAlertDialog(context, "The movie was created", "Success", MoviesListActivity::class)
            } else {
                Helpers.showAlertDialog(context, "You seem not authorized to do that..", "Error", null)
            }
        }

    }


    }