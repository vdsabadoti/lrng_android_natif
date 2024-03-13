package com.example.demoeni

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActiviyRegistrationBinding
import com.example.demoeni.services.RegistrationService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class RegisterActivity : ComponentActivity() {

    lateinit var myView : ActiviyRegistrationBinding;
    val registerViewModel : RegisterViewModel by viewModels();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = DataBindingUtil.setContentView(this, R.layout.activiy_registration);
        myView.lifecycleOwner = this

        myView.registration = registerViewModel;

        myView.loginBtn.setOnClickListener(){
            registerViewModel.loginPage(this@RegisterActivity);
        }

        myView.singUpBtn.setOnClickListener(){
            registerViewModel.createPerson(this@RegisterActivity, registerViewModel.person)
        }

    }


}

