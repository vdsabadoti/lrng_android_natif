package com.example.demoeni.utils

class User(var mail: String? = "", var nickname: String? = "", var password: String? = "", var city: String? = "", var postalCode : String? = null,
           var phoneNumber : String? = null, var token : Any? = "") {

    fun tokenExist() : Boolean {
        return (token != "");
    }

    fun setValidToken(dataToken : Any?) {
        token = dataToken;
    }

    fun getValidToken() : Any? {
        return token;
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