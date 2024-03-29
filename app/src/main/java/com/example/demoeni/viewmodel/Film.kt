package com.example.demoeni.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Film(var id: Int, var title: String? ="", var synopsis: String? ="", var duration: String? ="", var year: String? = "") :
    Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Film) return false
        if (id == other.id) return false
        return super.equals(other);
    }

    override fun hashCode(): Int {
        return id ?: 0
    }
}