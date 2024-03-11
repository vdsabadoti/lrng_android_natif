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
import com.example.demoeni.viewmodel.User
import kotlinx.coroutines.launch

class MovieDeleteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        //Envoyer une requete POST à l'API
        lifecycleScope.launch {
            val response = MovieService.MovieApi.retrofitService.delete(User.getToken(), id);
            if (response.code == "200") {
                val builder = AlertDialog.Builder(this@MovieDeleteActivity);
                builder.setTitle("Delete");
                builder.setMessage("Movie deleted");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                //afficher le modal
                builder.show();
            }
            val intent = Intent(applicationContext, MoviesListActivity::class.java);
            startActivity(intent);
        }



        }
    }