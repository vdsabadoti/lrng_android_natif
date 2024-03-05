package com.example.demoeni

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //liaison avec le xml (pour la vue)
        setContentView(R.layout.activiy_registration)
    }

    private fun openActivity(classType : KClass<*>){
        var intent = Intent(this, classType.java);
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

}

