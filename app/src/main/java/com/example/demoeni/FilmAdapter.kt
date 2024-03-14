package com.example.demoeni

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoeni.databinding.CellFilmBinding
import com.example.demoeni.services.MovieService
import com.example.demoeni.viewmodel.AuthContextViewModel
import com.example.demoeni.viewmodel.Film
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class FilmAdapter : ListAdapter<Film, FilmAdapter.ViewHolder>(FilmDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        return ViewHolder.from(parent);
    }

    override fun onBindViewHolder(holder : ViewHolder, position: Int) {
        val film = getItem(position);
        holder.bind(film);
    }

    class ViewHolder(val binding : CellFilmBinding, val parent: ViewGroup) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Film) {
            binding.film = data;
            var authContextViewModel = AuthContextViewModel();
            binding.authContext = authContextViewModel;

            //Experimental : charger url sur la cellule
            //Picasso.get().load(data.thumbnailUrl).into(binding.image1)

            binding.detail.setOnClickListener(){
                openActivity(MovieDetailActivity::class, data.id)
            }

            binding.edit.setOnClickListener(){
                openActivity(MovieEditActivity::class, data.id)
            }

            binding.delete.setOnClickListener(){
                openActivity(MovieDeleteActivity::class, data.id)
            }


            authContextViewModel.getAuthRegistry()?.bLogged?.observe(parent.context as LifecycleOwner, Observer {
                binding.authContext =binding.authContext;
            })

            binding.executePendingBindings();


            }

        private fun openActivity(classType: KClass<*>, tag: Int?){
            val intent = Intent(binding.root.context, classType.java);
            //si on souhaite, on peut mettre des paramètres
            intent.putExtra("id", tag);

            //ouvrir
            binding.root.context.startActivity(intent);
        }

        companion object {

            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CellFilmBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, parent);
            }

        }

    }


}