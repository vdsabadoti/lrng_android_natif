package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.demoeni.databinding.ActivityMovieEditBinding
import com.example.demoeni.viewmodel.MovieEditViewModel

class MovieEditActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieEditBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_edit);
        vm.lifecycleOwner = this;
        val movieEditViewModel = MovieEditViewModel(id);
        vm.movieEditViewModel = movieEditViewModel;

        movieEditViewModel.movie.observe(this, Observer {
            vm.movieEditViewModel = vm.movieEditViewModel;
        })

        vm.save.setOnClickListener(){
            movieEditViewModel.save(this@MovieEditActivity);
        }

        movieEditViewModel.getMovie(this@MovieEditActivity);
    }



    }