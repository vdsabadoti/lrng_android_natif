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

        lifecycleScope.launch {
            val movies = MovieService.MovieApi.retrofitService.getMovies()
            adapter.submitList(movies);
        }

        }

        fun refresh(){
            lifecycleScope.launch {
                val movies = MovieService.MovieApi.retrofitService.getMovies()
                adapter.submitList(movies);
            }
        }

        fun detail(view: View){
            val tag = Integer.parseInt(view.tag.toString());
            openActivity(MovieDetailActivity::class, tag)
            }

        fun edit(view: View){
            val tag = Integer.parseInt(view.tag.toString());
            openActivity(MovieEditActivity::class, tag)
        }

        fun delete(view: View){
    }

    private fun openActivity(classType: KClass<*>, tag: Int?){
        val intent = Intent(this, classType.java);
        //si on souhaite, on peut mettre des paramètres
        intent.putExtra("id", tag);

        //ouvrir
        startActivity(intent);
    }


    }