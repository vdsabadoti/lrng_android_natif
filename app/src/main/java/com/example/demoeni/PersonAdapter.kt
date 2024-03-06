package com.example.demoeni

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoeni.databinding.CellPersonBinding
import com.example.demoeni.viewmodel.Person

//la passarelle pour connecter une celulle dans un recyclerView
class PersonAdapter : ListAdapter<Person, PersonAdapter.ViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = getItem(position);
        holder.bind(person);
    }

    //OVERIDE
    //classe interne => permet de determiner comment on charge/connecte/lie les données avec la cellule
    class ViewHolder(val binding : CellPersonBinding) : RecyclerView.ViewHolder(binding.root) {
        //faire le lien entre donnéee envoyée et la vue
        fun bind(data : Person) {
            binding.person = data ;
            binding.executePendingBindings();
        }

        //raccoursi pour dire que tout ce qui est à l'interieur c'est statique
        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
              val layoutInflater = LayoutInflater.from(parent.context)
              val binding = CellPersonBinding.inflate(layoutInflater, parent, false);

              return ViewHolder(binding);
            };
        }

    }



}