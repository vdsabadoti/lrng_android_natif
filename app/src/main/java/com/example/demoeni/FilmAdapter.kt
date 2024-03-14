package com.example.demoeni

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
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

    class ViewHolder(val binding : CellFilmBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : Film) {
            binding.film = data;
            binding.authContext = AuthContextViewModel()

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

                return ViewHolder(binding);
            }

        }

    }


}