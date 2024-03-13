package com.example.demoeni.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.utils.Helpers
import androidx.lifecycle.viewModelScope
import com.example.demoeni.FilmAdapter
import com.example.demoeni.LoginActivity
import com.example.demoeni.MovieRegisterActivity
import com.example.demoeni.MoviesListActivity
import com.example.demoeni.services.LoginService
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MovieListsViewModel(var adapter: FilmAdapter = FilmAdapter(), var movies : MutableLiveData<List<Film>> = MutableLiveData()) :
    ViewModel() {

    fun refresh(context: Context){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = MovieService.MovieApi.retrofitService.getMoviesV2(User.getInstance()?.getValidToken());
            if (response.code == "200") {
                movies.value = response.data
            } else {
                Helpers.showAlertDialog(context, "No movies for you", "Error", LoginActivity::class)
            }
            Helpers.closeProgressDialog();
        }
    }

    fun create(context: Context){
        Helpers.openActivity(context, MovieRegisterActivity::class)
    }

    }