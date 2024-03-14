package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demoeni.databinding.ActivityMainBinding
import com.example.demoeni.utils.Helpers
import com.example.demoeni.utils.User
import com.example.demoeni.viewmodel.AuthContextViewModel

class MainActivity : ComponentActivity() {

    lateinit var vm : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = DataBindingUtil.setContentView(this, R.layout.activity_main);

        val authContextViewModel = ViewModelProvider(this).get(AuthContextViewModel::class.java);
        vm.authContext = authContextViewModel;

        authContextViewModel.getAuthRegistry()?.bLogged!!.observe(this, Observer {
            vm.authContext =  vm.authContext;
        })

        vm.loginBtn.setOnClickListener(){
            Helpers.openActivity(this@MainActivity, LoginActivity::class)
        }

        vm.signupBtn.setOnClickListener(){
            Helpers.openActivity(this@MainActivity, RegisterActivity::class)
        }

        vm.moviesBtn.setOnClickListener(){
                    Helpers.openActivity(this@MainActivity, MoviesListActivity::class)
                }

        vm.logoutBtn.setOnClickListener(){
                    User.getInstance()?.logout();
                }

        vm.myProfileBtn.setOnClickListener(){
            Helpers.openActivity(this@MainActivity, ModifProfilActivity::class)
        }

    }

}

