package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActiviyRegistrationBinding
import com.example.demoeni.services.RegistrationService
import com.example.demoeni.viewmodel.Person
import com.example.demoeni.viewmodel.RegisterViewModel
import com.example.demoeni.viewmodel.User
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class RegisterActivity : ComponentActivity() {

    lateinit var myView : ActiviyRegistrationBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = DataBindingUtil.setContentView(this, R.layout.activiy_registration);

        myView.registration = RegisterViewModel(User(), "");

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
        lifecycleScope.launch {
            val response = RegistrationService.RegistrationApi.retrofitService.registration(myView.registration?.user!!)
            if (response.code == "200") {
                val builder = AlertDialog.Builder(this@RegisterActivity);
                builder.setTitle("Registration");
                builder.setMessage("User created with success");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                };
                //afficher le modal
                builder.show();
            }
        }
    }


}

