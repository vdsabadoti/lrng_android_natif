package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMovieRegisterBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.viewmodel.Film
import com.example.demoeni.utils.User
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
                val response = MovieService.MovieApi.retrofitService.editMovieById(User.getInstance()?.getValidToken(), vm.movie);
                if (response.code == "202") {
                    //le code pour construire un modal
                    val builder = AlertDialog.Builder(this@MovieRegisterActivity);
                    builder.setTitle("Loading");
                    builder.setMessage("Are you sure you qant to create ?");
                    builder.setPositiveButton("Yes") { dialog, which ->
                        dialog.dismiss();
                        val intent = Intent(applicationContext, MoviesListActivity::class.java);
                        startActivity(intent);
                    };
                    //afficher le modal
                    builder.show();
                } else {
                    val builder = AlertDialog.Builder(this@MovieRegisterActivity);
                    builder.setTitle("Not autho");
                    builder.setMessage("Not auth");
                    builder.setPositiveButton("Yes") { dialog, which ->
                        dialog.dismiss();
                    };
                    //afficher le modal
                    builder.show();
                }
            }

        }
    }