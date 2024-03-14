package com.example.demoeni.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.demoeni.utils.User

class AuthContextViewModel() : ViewModel() {

    fun isLogged() : Boolean {
        return User.getInstance()?.tokenExist()!!;
    }

    fun getAuthRegistry() : User? {
        return User.getInstance()
    }

    fun isLoggedVisibility(inverse : Boolean) : Int {
        if (isLogged()){
            return if (inverse) View.GONE else View.VISIBLE
        }
        return if (inverse) View.VISIBLE else View.GONE

    }
}