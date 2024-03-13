package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieDetailBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.Film
import com.example.demoeni.viewmodel.LoginViewModel
import com.example.demoeni.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.launch

class MovieDetailActivity : ComponentActivity() {

    lateinit var vm: ActivityMovieDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        vm.lifecycleOwner = this;
        val movieDetailViewModel = MovieDetailViewModel(id);
        vm.movieDetailViewModel = movieDetailViewModel;

        movieDetailViewModel.movie.observe(this, Observer {
            vm.movieDetailViewModel = vm.movieDetailViewModel;
        })

        movieDetailViewModel.getMovie(this@MovieDetailActivity);
    }

}