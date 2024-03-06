package com.example.demoeni

import androidx.recyclerview.widget.DiffUtil
import com.example.demoeni.viewmodel.Film

class FilmDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id;
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.equals(newItem);
    }


}