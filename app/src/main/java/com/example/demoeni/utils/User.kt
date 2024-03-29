package com.example.demoeni.utils

import androidx.lifecycle.MutableLiveData

class User(var token : Any? = "", var mail : String? = "", var bLogged : MutableLiveData<Boolean> = MutableLiveData(false)) {

    val tokenThreadLooper = TokenThreadLooper();

    fun tokenExist() : Boolean {
        return (token != "");
    }

    fun setValidToken(dataToken : Any?) {
        token = dataToken;
        bLogged.value = true;
        //launch thread to check validity of thread (each 5s)
        tokenThreadLooper.start(5000)
    }

    fun setMailPersonInSession(dataMail : String?) {
        mail = dataMail;
    }

    fun getValidToken() : Any? {
        return token;
    }

    fun getMailPersonInSession() : String? {
        return mail;
    }

    fun logout(){
        mail = "";
        token = "";
        bLogged.postValue(false);
    }

    companion object {

        private var instance : User? = null;

        fun getInstance() : User? {
            if (instance != null){
                return instance;
            }
            instance = User();
            return instance;
        }


    }

}