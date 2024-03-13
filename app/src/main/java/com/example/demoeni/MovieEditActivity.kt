package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieEditBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.utils.User
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

            val response = MovieService.MovieApi.retrofitService.getMovieById(User.getInstance()?.getValidToken(), id);
            if (response.code == "200") {
                vm.movie = response.data;
            }
        }
        }
        private fun save(){
            lifecycleScope.launch {
                Helpers.showProgressDialog(this@MovieEditActivity, "Loading");
                val response = MovieService.MovieApi.retrofitService.editMovieById(User.getInstance()?.getValidToken(), vm.movie?.id, vm.movie);
                Helpers.closeProgressDialog()
                if (response.code == "200") {
                    vm.movie = response.data;
                    Helpers.showAlertDialog(this@MovieEditActivity, "The movie was updated", "Success", MoviesListActivity::class)
                } else {
                    Helpers.showAlertDialog(this@MovieEditActivity, "You seem not authorized to do that..", "Error", MoviesListActivity::class)
                }
            }
        }
    }