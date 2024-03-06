package com.example.demoeni

import androidx.recyclerview.widget.DiffUtil
import com.example.demoeni.viewmodel.Person

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.mail == newItem.mail;
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.equals(newItem);
    }


}