package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.demoeni.databinding.ActivityMoviesListBinding
import com.example.demoeni.viewmodel.Film

class MoviesListActivity : ComponentActivity() {

    lateinit var vm : ActivityMoviesListBinding;
    lateinit var dataList : MutableLiveData<MutableList<Film>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_movies_list);

        val adapter = FilmAdapter();
        vm.rvFilms.adapter = adapter;

        dataList = MutableLiveData<MutableList<Film>>(mutableListOf(
            Film(1, "A start is born", "Lady Gaga is on the screen",
                "120 min", "2019"),
            Film(2, "Dune 2", "Sci-fi tomorrow I\'ll tell you",
                "200 min", "2024")))

        adapter.submitList(dataList.value);

        }
    }