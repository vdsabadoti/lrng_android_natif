package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMoviesListBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.viewmodel.Film
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.LoginViewModel
import com.example.demoeni.viewmodel.MovieListsViewModel
import kotlinx.coroutines.launch

class MoviesListActivity : ComponentActivity() {

    lateinit var vm : ActivityMoviesListBinding;
    val moviesListsViewModel : MovieListsViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movies_list);
        vm.lifecycleOwner = this;
        vm.moviesListsViewModel = moviesListsViewModel;

        vm.rvFilms.adapter = moviesListsViewModel.adapter;

        //If inside moviesListViewModel the movies observable change its value
        //this function is called and the movies list is sent to the adapter
        moviesListsViewModel.movies.observe(this, Observer{
            moviesListsViewModel.adapter.submitList(it)
        })

        //call API to get movies from DB if button is clicked
        vm.refresh.setOnClickListener {
            moviesListsViewModel.refresh(this@MoviesListActivity);
        }

        //call function to change activity : create movie activity
        vm.newMovie.setOnClickListener {
            moviesListsViewModel.create(this@MoviesListActivity);
        }

        //Call API to get movies from DB
        moviesListsViewModel.refresh(this@MoviesListActivity);

        }

    }