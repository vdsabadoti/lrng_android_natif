package com.example.demoeni.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoeni.utils.Helpers
import androidx.lifecycle.viewModelScope
import com.example.demoeni.MoviesListActivity
import com.example.demoeni.services.LoginService
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MovieEditViewModel(var id : Int, var movie: MutableLiveData<Film> = MutableLiveData()) :
    ViewModel() {

    fun getMovie(context: Context){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = MovieService.MovieApi.retrofitService.getMovieByIdWithAuth(User.getInstance()?.getValidToken(), id);
            Helpers.closeProgressDialog();
            if (response.code == "200") {
                movie.value = response.data!!;
            } else {
                Helpers.showAlertDialog(context, "You seem not authorized to do that..", "Error", null)
            }
            //Picasso.get().load("https://upload.wikimedia.org/wikipedia/en/0/0c/The_VelociPastor.jpg").into(vm.thumbnail)
        }
    }

    fun save(context: Context){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = MovieService.MovieApi.retrofitService.editMovieById(User.getInstance()?.getValidToken(), movie.value!!.id, movie.value!!);
            Helpers.closeProgressDialog()
            if (response.code == "200") {
                movie.value = response.data!!;
                Helpers.showAlertDialog(context, "The movie was updated", "Success", MoviesListActivity::class)
            } else {
                Helpers.showAlertDialog(context, "You seem not authorized to do that..", "Error", MoviesListActivity::class)
            }
        }
    }


    }