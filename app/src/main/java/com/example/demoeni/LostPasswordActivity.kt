package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.example.demoeni.databinding.ActivityLostPasswordBinding
import com.example.demoeni.utils.Helpers
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
        Helpers.showAlertDialog(this@LostPasswordActivity, "An email was sent to ${myView.viewModel?.mail}", "Email sent", null)
    }
}

