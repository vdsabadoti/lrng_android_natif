package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieRegisterBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.viewmodel.Film
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.LoginViewModel
import com.example.demoeni.viewmodel.MovieRegisterViewModel
import kotlinx.coroutines.launch

class MovieRegisterActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieRegisterBinding;
    val movieRegisterViewModel : MovieRegisterViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_register);
        vm.lifecycleOwner = this
        vm.movieRegisterViewModel = movieRegisterViewModel;

        vm.create.setOnClickListener(){
            movieRegisterViewModel.create(this@MovieRegisterActivity);
        }

        }

    }