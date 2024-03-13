package com.example.demoeni.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.demoeni.utils.Helpers
import androidx.lifecycle.viewModelScope
import com.example.demoeni.MoviesListActivity
import com.example.demoeni.services.LoginService
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class LoginViewModel(var person: Person = Person()) :
    ViewModel() {

    val forgotPassword : String = "Forgot password ?"
    val signUp : String = "Sign Up"

    fun newPage(context : Context, classType : KClass<*>) {
        Helpers.openActivity(context, classType)
    }

    fun login(context: Context, person: Person){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = LoginService.LoginApi.retrofitService.login(person)
            if (response.code == "200") {
                User.getInstance()?.setValidToken(response.data);
                Helpers.closeProgressDialog();
                Helpers.showAlertDialog(context, "You are connected", "Success", MoviesListActivity::class)
            } else {
                Helpers.closeProgressDialog();
                Helpers.showAlertDialog(context, "Wrong credentials", "Error", null)
            }
        }
    }
    }