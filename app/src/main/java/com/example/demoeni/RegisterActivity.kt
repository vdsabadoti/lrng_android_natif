package com.example.demoeni

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.example.demoeni.databinding.ActiviyRegistrationBinding
import kotlin.reflect.KClass

class RegisterActivity : ComponentActivity() {

    lateinit var myView : ActiviyRegistrationBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //liaison avec le xml (pour la vue)
        //setContentView(R.layout.activiy_registration)

        //Recuperer la vue en version DataBinding
        //Puisqu'on a fait <layout> avec la dependance DataBinding sur la vue, il y a un classe invisible qui s'est crée derrière
        //Dont on a besoin de faire appel ici |v|
        myView = DataBindingUtil.setContentView<ActiviyRegistrationBinding>(this, R.layout.activiy_registration);

        //Instatiation of a value
        var person = Person("","Mulan","");

        //Bind value to view thanks to DataBinding
        myView.person = person;

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

