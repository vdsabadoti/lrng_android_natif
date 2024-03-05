package com.example.demoeni

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //liaison avec le xml (pour la vue)
        setContentView(R.layout.activity_login)

        //on recupère les elements de la vue par ID
        val mTextViewForgotPassword = findViewById<TextView>(R.id.forgot_password)
        val mTextViewSignUp = findViewById<TextView>(R.id.sign_up)

        // Declaring strings
        val sForgotPassword = "Forgot password ?"
        val sSignUp = "Sign up"

        // Creating a Spannable String
        // from the above string
        val sForgotPasswordSpannable = SpannableString(sForgotPassword)
        val sSignUpSpannable = SpannableString(sSignUp)

        // Setting underline style from
        // position 0 till length of
        // the spannable string
        sForgotPasswordSpannable.setSpan(UnderlineSpan(), 0, sForgotPasswordSpannable.length, 0)
        sSignUpSpannable.setSpan(UnderlineSpan(), 0, sSignUpSpannable.length, 0)

        // Displaying spannable
        // string in TextView
        mTextViewForgotPassword.text = sForgotPasswordSpannable
        mTextViewSignUp.text = sSignUpSpannable


    }
    private fun openActivity(classType : KClass<*>){
        var intent = Intent(this, classType.java);
        //si on souhaite, on peut mettre des paramètres
        intent.putExtra("id", 10.0);

        //ouvrir
        startActivity(intent);
    }

    fun onClickTextSignUp(view: View){
        openActivity(RegisterActivity::class)
    }

    fun onClickTextLostPassword(view: View){
        openActivity(LostPasswordActivity::class)
    }

}

