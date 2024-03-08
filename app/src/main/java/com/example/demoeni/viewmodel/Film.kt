package com.example.demoeni.viewmodel

class Film(var id: Int, var title: String? ="", var synopsis: String? ="", var duration: String? ="", var year: String? = "") {

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