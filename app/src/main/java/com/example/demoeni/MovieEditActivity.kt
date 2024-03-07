package com.example.demoeni

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieDetailBinding
import com.example.demoeni.databinding.ActivityMovieEditBinding
import com.example.demoeni.services.MovieService
import kotlinx.coroutines.launch

class MovieEditActivity : ComponentActivity() {

    lateinit var vm : ActivityMovieEditBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_edit);

        vm.save.setOnClickListener(){
            save();
        }

        //Recuperer les données d'un API
        lifecycleScope.launch {
            val movie = MovieService.MovieApi.retrofitService.getMovieById(id);
            vm.movie = movie;
        }

        }

        fun save(){
            //le code pour construire un modal
            val builder = AlertDialog.Builder(this);
            builder.setTitle("Loading");
            builder.setMessage("Edit OK");
            builder.setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss();
            };
            //afficher le modal
            builder.show();
        }
    }