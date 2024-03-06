package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.example.demoeni.databinding.ActiviyRegistrationBinding
import com.example.demoeni.viewmodel.Person
import com.example.demoeni.viewmodel.RegisterViewModel
import kotlin.reflect.KClass

class RegisterActivity : ComponentActivity() {

    lateinit var myView : ActiviyRegistrationBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = DataBindingUtil.setContentView(this, R.layout.activiy_registration);

        myView.registration = RegisterViewModel(Person(), "");

    }

    private fun openActivity(classType : KClass<*>){
        val intent = Intent(this, classType.java);
        //si on souhaite, on peut mettre des paramètres
        intent.putExtra("id", 10.0);

        //ouvrir
        startActivity(intent);
    }

    fun onClickBtnDemo(view: View){
        openActivity(LoginActivity::class)
    }

    fun onClickTextLogin(view: View){
        openActivity(LoginActivity::class)
    }

    fun onClickModalDisplay(view: View){
        //le code pour construire un modal
        val builder = AlertDialog.Builder(this);
        builder.setTitle("Registration");
        builder.setMessage("Your account is being created."  +
                "Mail : ${myView.registration?.person?.mail}" +
                "Nickname : ${myView.registration?.person?.nickname}" +
                "City : ${myView.registration?.person?.city}");
        builder.setPositiveButton("Ok") { dialog, which ->
            dialog.dismiss();
        };
        //afficher le modal
        builder.show();
    }


}

