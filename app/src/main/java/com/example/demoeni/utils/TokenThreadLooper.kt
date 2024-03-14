package com.example.demoeni.utils

import com.example.demoeni.services.LoginService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TokenThreadLooper {

    var isRunning : Boolean = false;

    fun start(interval : Long) {
        if (!isRunning) {
            isRunning = true;
            GlobalScope.launch {

                while (isRunning){
                    delay(interval)
                    val token = User.getInstance()?.token;
                    var response = LoginService.LoginApi.retrofitService.verifyToken(token.toString());
                    if (response.code != "200"){
                        stop();
                        User.getInstance()?.logout();
                    }
                }

            }

            }
        }

    private fun stop(){
        isRunning = false;
    }

}