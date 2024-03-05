package com.example.demoeni

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //liaison avec le xml (pour la vue)
        setContentView(R.layout.activity_main)
    }

    //signature attendue pour un evenemenet onClick : view: View
    fun onClickBtnDemo(view: View){
        //ouvrir la page de login
        var intent = Intent(this, LoginActivity::class.java);
        //si on souhaite, on peut mettre des paramètres
        intent.putExtra("id", 10.0);

        //ouvrir
        startActivity(intent);
    }
}

