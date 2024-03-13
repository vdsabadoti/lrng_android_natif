package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.demoeni.databinding.ActivityLostPasswordBinding
import com.example.demoeni.utils.Helpers
import com.example.demoeni.viewmodel.ForgotPasswordViewModel
import com.example.demoeni.viewmodel.LoginViewModel
import kotlin.reflect.KClass

class LostPasswordActivity : ComponentActivity() {

    lateinit var myView : ActivityLostPasswordBinding;
    val lostPasswordViewModel : ForgotPasswordViewModel by viewModels();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myView = DataBindingUtil.setContentView(this, R.layout.activity_lost_password);
        myView.lifecycleOwner = this;
        myView.viewModel = lostPasswordViewModel;

        myView.lostPasswordBtn.setOnClickListener(){
            lostPasswordViewModel.sendMailRecup(this@LostPasswordActivity, lostPasswordViewModel.mail)
        }

        myView.loginBtn.setOnClickListener(){
            lostPasswordViewModel.newPage(this@LostPasswordActivity, LoginActivity::class);
        }

    }


}

