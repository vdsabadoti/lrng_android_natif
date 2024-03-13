package com.example.demoeni

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityLoginBinding
import com.example.demoeni.services.LoginService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.Person
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class LoginActivity : ComponentActivity() {

    lateinit var vm : ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        //liaison avec le xml (pour la vue)
        vm = DataBindingUtil.setContentView(this, R.layout.activity_login);
        vm.person = Person();

        //on recupère les elements de la vue par ID
        val mTextViewForgotPassword = vm.forgotPassword;
        val mTextViewSignUp = vm.signUp;

        // Declaring strings
        val sForgotPassword = "Forgot password ?";
        val sSignUp = "Sign up";

        // Creating a Spannable String
        // from the above string
        val sForgotPasswordSpannable = SpannableString(sForgotPassword);
        val sSignUpSpannable = SpannableString(sSignUp);

        // Setting underline style from
        // position 0 till length of
        // the spannable string
        sForgotPasswordSpannable.setSpan(UnderlineSpan(), 0, sForgotPasswordSpannable.length, 0);
        sSignUpSpannable.setSpan(UnderlineSpan(), 0, sSignUpSpannable.length, 0);

        // Displaying spannable
        // string in TextView
        mTextViewForgotPassword.text = sForgotPasswordSpannable;
        mTextViewSignUp.text = sSignUpSpannable;


    }
    private fun openActivity(classType : KClass<*>){
        var intent = Intent(this, classType.java);
        //si on souhaite, on peut mettre des paramètres
        intent.putExtra("id", 10.0);

        //ouvrir
        startActivity(intent);
    }

    fun onClickTextSignUp(view: View){
        openActivity(RegisterActivity::class);
    }

    fun onClickTextLostPassword(view: View){
        openActivity(LostPasswordActivity::class);
    }

    fun onClickModalDisplay(view: View){
        //le code pour construire un modal
        lifecycleScope.launch {
            Helpers.showProgressDialog(this@LoginActivity, "Loading");
            val response = LoginService.LoginApi.retrofitService.login(vm.person!!)
            if (response.code == "200") {
                User.getInstance()?.setValidToken(response.data);
                Helpers.closeProgressDialog();
                Helpers.showAlertDialog(this@LoginActivity, "You are connected", "Success", MoviesListActivity::class)
            } else {
                Helpers.closeProgressDialog();
                Helpers.showAlertDialog(this@LoginActivity, "Wrong credentials", "Error", null)
            }
        }
    }
}

