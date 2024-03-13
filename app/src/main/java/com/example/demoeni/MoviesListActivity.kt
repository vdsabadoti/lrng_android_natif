package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityMoviesListBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.viewmodel.Film
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch

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

        private fun refresh(){
            lifecycleScope.launch {
                Helpers.showProgressDialog(this@MoviesListActivity, "Loading");
                val response = MovieService.MovieApi.retrofitService.getMoviesV2(User.getInstance()?.getValidToken());
            if (response.code == "200") {
                adapter.submitList(response.data);
            } else {
                Helpers.showAlertDialog(this@MoviesListActivity, "No movies for you", "Error", LoginActivity::class)
            }
                Helpers.closeProgressDialog();
            }
        }

        private fun create(){
            Helpers.openActivity(this, MovieRegisterActivity::class)
        }

    }