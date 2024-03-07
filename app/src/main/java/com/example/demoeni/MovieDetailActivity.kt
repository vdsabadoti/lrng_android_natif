package com.example.demoeni

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieDetailBinding
import com.example.demoeni.services.MovieService
import kotlinx.coroutines.launch

class MovieDetailActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        //Recuperer les données d'un API
        lifecycleScope.launch {
            val movie = MovieService.MovieApi.retrofitService.getMovieById(id);
            vm.movie = movie;
        }

        }

        fun refresh(view: View){
            lifecycleScope.launch {
                val movie = MovieService.MovieApi.retrofitService.getMovieById(1);
                vm.movie = movie;
            }
        }
    }