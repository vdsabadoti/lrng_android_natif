package com.example.demoeni

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityLoginBinding
import com.example.demoeni.services.LoginService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.LoginViewModel
import com.example.demoeni.viewmodel.Person
import com.example.demoeni.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class LoginActivity : ComponentActivity() {

    lateinit var vm : ActivityLoginBinding;
    val loginViewModel : LoginViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        vm = DataBindingUtil.setContentView(this, R.layout.activity_login);
        vm.lifecycleOwner = this;
        vm.loginViewModel = loginViewModel;

        vm.forgotPassword.setOnClickListener(){
            loginViewModel.newPage(this@LoginActivity, LostPasswordActivity::class);
        }

        vm.signUp.setOnClickListener(){
            loginViewModel.newPage(this@LoginActivity, RegisterActivity::class);
        }

        vm.login.setOnClickListener(){
            loginViewModel.login(this@LoginActivity)
        }

    }
}

