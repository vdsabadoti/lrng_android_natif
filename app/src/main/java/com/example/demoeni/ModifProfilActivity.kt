package com.example.demoeni

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityLoginBinding
import com.example.demoeni.databinding.ActivityModifUserprofileBinding
import com.example.demoeni.databinding.ActiviyRegistrationBinding
import com.example.demoeni.services.RegistrationService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class ModifProfilActivity : ComponentActivity() {

    lateinit var myView : ActivityModifUserprofileBinding;
    val registerViewModel : RegisterViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = DataBindingUtil.setContentView(this, R.layout.activity_modif_userprofile);
        myView.lifecycleOwner = this

        myView.profilModifModelView = registerViewModel;

        myView.save.setOnClickListener(){
            registerViewModel.modifPerson(this@ModifProfilActivity, );
        }

        registerViewModel.person.observe(this, Observer {
            myView.profilModifModelView = myView.profilModifModelView;
        })

        registerViewModel.getPerson(this@ModifProfilActivity, User.getInstance()?.getMailPersonInSession()!!)
    }

}

