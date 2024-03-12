package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieDetailBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch

class MovieDetailActivity : ComponentActivity() {

    lateinit var vm: ActivityMovieDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        //Recuperer les données d'un API
        lifecycleScope.launch {
            val response = MovieService.MovieApi.retrofitService.getMovieById(User.getInstance()?.getValidToken(), id);
            if (response.code == "200") {
                vm.movie = response.data;
            } else {
                val builder = AlertDialog.Builder(this@MovieDetailActivity);
                builder.setTitle("Not authorized");
                builder.setMessage(response.message);
                builder.setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();
            }

            //Picasso.get().load("https://upload.wikimedia.org/wikipedia/en/0/0c/The_VelociPastor.jpg").into(vm.thumbnail)
        }

    }

}