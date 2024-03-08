package com.example.demoeni

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMoviesListBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.viewmodel.Film
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MoviesListActivity : ComponentActivity() {

    lateinit var vm : ActivityMoviesListBinding;
    lateinit var dataList : MutableLiveData<MutableList<Film>>
    var adapter = FilmAdapter() ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movies_list);

        vm.rvFilms.adapter = adapter;

        vm.refresh.setOnClickListener {
            refresh();
        }
        vm.newMovie.setOnClickListener {
            create();
        }
        refresh();

        }

        fun refresh(){
            lifecycleScope.launch {
                //TODO Call modal
                val movies = MovieService.MovieApi.retrofitService.getMovies()
                adapter.submitList(movies);
                //TODO Close modal
            }
        }

        fun create(){
            openActivity(MovieRegisterActivity::class)
        }

    private fun openActivity(classType: KClass<*>){
        val intent = Intent(this, classType.java);
        startActivity(intent);
    }


    }