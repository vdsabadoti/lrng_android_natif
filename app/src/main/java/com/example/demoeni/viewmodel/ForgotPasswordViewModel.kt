package com.example.demoeni.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.demoeni.utils.Helpers
import kotlin.reflect.KClass

class ForgotPasswordViewModel(var mail: String = "") : ViewModel() {

    fun sendMailRecup(context : Context, mail : String){
        Helpers.showAlertDialog(context, "An email was sent to ${mail}", "Email sent", null)
    }

    fun newPage(context : Context, classType : KClass<*>) {
        Helpers.openActivity(context, classType)
    }

}