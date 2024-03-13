package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieRegisterBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.viewmodel.Film
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch

class MovieRegisterActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieRegisterBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_register);

        vm.create.setOnClickListener(){
            create();
        }

        vm.movie = Film(10);

        }
        fun create(){
            lifecycleScope.launch {
                Helpers.showProgressDialog(this@MovieRegisterActivity, "Loading");
                val response = MovieService.MovieApi.retrofitService.editMovieById(User.getInstance()?.getValidToken(), vm.movie);
                Helpers.closeProgressDialog()
                if (response.code == "202") {
                    Helpers.showAlertDialog(this@MovieRegisterActivity, "The movie was created", "Success", MoviesListActivity::class)
                } else {
                    Helpers.showAlertDialog(this@MovieRegisterActivity, "You seem not authorized to do that..", "Error", null)
                }
            }

        }
    }