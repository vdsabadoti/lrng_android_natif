package com.example.demoeni.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoeni.LoginActivity
import com.example.demoeni.utils.Helpers
import androidx.lifecycle.viewModelScope
import com.example.demoeni.MainActivity
import com.example.demoeni.services.RegistrationService
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch

class RegisterViewModel(var person: MutableLiveData<Person> = MutableLiveData(Person()), var passwordConfirmation: String = "") :
    ViewModel() {

        public val textValue : String = "Log in"
    fun loginPage(context : Context) {
        Helpers.openActivity(context, LoginActivity::class)
    }

    fun createPerson(context: Context){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = RegistrationService.RegistrationApi.retrofitService.registration(person.value);
            Helpers.closeProgressDialog();
            if (response.code == "200") {
                Helpers.showAlertDialog(context, "User created with success", "Success", MainActivity::class)
            } else {
                Helpers.showAlertDialog(context, "Invalid champs", "Error", null)
            }
        }
    }

    fun modifPerson(context: Context){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = RegistrationService.RegistrationApi.retrofitService.modifPerson(User.getInstance()?.getValidToken(),person.value!!);
            Helpers.closeProgressDialog();
            if (response.code == "200") {
                Helpers.showAlertDialog(context, "User updated with success", "Success", MainActivity::class)
            } else {
                Helpers.showAlertDialog(context, "Invalid champs", "Error", null)
            }
        }
    }

    fun getPerson(context: Context, mail : String){
        viewModelScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = RegistrationService.RegistrationApi.retrofitService.getPerson(User.getInstance()?.getValidToken(), mail);
            Helpers.closeProgressDialog();
            if (response.code == "200") {
                person.value = response.data!!;
            } else {
                Helpers.showAlertDialog(context, "Something is wrong", "Error", null)
            }
        }
    }


    }