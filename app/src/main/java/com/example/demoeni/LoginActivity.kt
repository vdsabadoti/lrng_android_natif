package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityLoginBinding
import com.example.demoeni.services.LoginService
import com.example.demoeni.services.MovieService
import com.example.demoeni.viewmodel.User
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class LoginActivity : ComponentActivity() {

    lateinit var vm : ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        //liaison avec le xml (pour la vue)
        vm = DataBindingUtil.setContentView(this, R.layout.activity_login);
        vm.user = User();

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

            val response = LoginService.LoginApi.retrofitService.login(vm.user!!)
            if (response.code == "200") {
                User.setToken(response.data);

                var builder = AlertDialog.Builder(this@LoginActivity);
                builder.setTitle(response.code);
                builder.setMessage(User.getToken().toString());
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                    val intent = Intent(applicationContext, MoviesListActivity::class.java);
                    startActivity(intent);
                };
                //afficher le modal
                builder.show();
            }



        }


    }

}

