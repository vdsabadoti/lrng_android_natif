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

            val response = MovieService.MovieApi.retrofitService.getMovieById(id);
            if (response.code == "200") {
                vm.movie = response.data;
            }
        }
        }
        private fun save(){
            lifecycleScope.launch {
                val response = MovieService.MovieApi.retrofitService.editMovieById(vm.movie?.id, vm.movie);
                if (response.code == "200") {
                    //Update the movie with the movie created in DB
                    vm.movie = response.data;
                    //Modal in the screen to announce successfully action
                    val builder = AlertDialog.Builder(applicationContext);
                    builder.setTitle("Update");
                    builder.setMessage(response.message);
                    builder.setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss();
                        val intent = Intent(applicationContext, MoviesListActivity::class.java);
                        startActivity(intent);
                    };
                    builder.show();
                }
            }
        }
    }