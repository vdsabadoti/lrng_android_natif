package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.example.demoeni.databinding.ActivityLostPasswordBinding
import com.example.demoeni.viewmodel.ForgotPasswordViewModel
import kotlin.reflect.KClass

class LostPasswordActivity : ComponentActivity() {

    lateinit var myView : ActivityLostPasswordBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = DataBindingUtil.setContentView(this, R.layout.activity_lost_password);

        val lostPassword = ForgotPasswordViewModel();

        myView.viewModel = lostPassword;

    }

    private fun openActivity(classType : KClass<*>){
        var intent = Intent(this, classType.java);
        //si on souhaite, on peut mettre des paramètres
        intent.putExtra("id", 10.0);

        //ouvrir
        startActivity(intent);
    }

    fun onClickTextLogin(view: View){
        openActivity(LoginActivity::class)
    }

    fun onClickModalDisplay(view: View){
        //le code pour construire un modal
        var builder = AlertDialog.Builder(this);
        builder.setTitle("Forgot password");
        builder.setMessage("Email sent to " +
                "${myView.viewModel?.mail}. " +
                "Please verify your inbox.");
        builder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss();
        };
        //afficher le modal
        builder.show();
    }


}

