package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieDetailBinding
import com.example.demoeni.databinding.ActivityMovieEditBinding
import com.example.demoeni.services.MovieService
import kotlinx.coroutines.launch

class MovieDeleteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        //Envoyer une requete POST à l'API
        lifecycleScope.launch {
            MovieService.MovieApi.retrofitService.delete(id);
        }

        val intent = Intent(this, MoviesListActivity::class.java);
        startActivity(intent);

        }
    }