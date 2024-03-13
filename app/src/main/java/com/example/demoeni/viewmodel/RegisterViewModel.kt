package com.example.demoeni.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.demoeni.LoginActivity
import com.example.demoeni.utils.Helpers
import androidx.lifecycle.viewModelScope
import com.example.demoeni.services.RegistrationService
import kotlinx.coroutines.launch

class RegisterViewModel(var person: Person = Person(), var passwordConfirmation: String = "") :
    ViewModel() {

        public val textValue : String = "Log in"
    fun loginPage(context : Context) {
        Helpers.openActivity(context, LoginActivity::class)
    }

    fun createPerson(context: Context, person: Person){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = RegistrationService.RegistrationApi.retrofitService.registration(person);
            Helpers.closeProgressDialog();
            if (response.code == "200") {
                Helpers.showAlertDialog(context, "User created with success", "Success", LoginActivity::class)
            } else {
                Helpers.showAlertDialog(context, "Invalid champs", "Error", null)
            }
        }
    }
    }