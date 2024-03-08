package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieDetailBinding
import com.example.demoeni.databinding.ActivityMovieEditBinding
import com.example.demoeni.databinding.ActivityMovieRegisterBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.viewmodel.Film
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
                MovieService.MovieApi.retrofitService.editMovieById(vm.movie)
            }
            //le code pour construire un modal
            val builder = AlertDialog.Builder(this);
            builder.setTitle("Loading");
            builder.setMessage("Are you sure you qant to create ?");
            builder.setPositiveButton("Yes") { dialog, which ->
                dialog.dismiss();
                val intent = Intent(this, MoviesListActivity::class.java);
                startActivity(intent);
            };
            //afficher le modal
            builder.show();
        }
    }