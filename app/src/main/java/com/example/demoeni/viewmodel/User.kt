package com.example.demoeni.viewmodel

class User(var mail: String? = "", var nickname: String? = "", var password: String? = "", var city: String? = "", var postalCode : String? = null,
           var phoneNumber : String? = null) {

    companion object {

        private var token : Any? = "";

        fun getToken() : Any? {
            return token;
        }

        fun setToken(dataToken : Any?) {
            token = dataToken;
        }

        fun logout() {
            token = "";
           // TODO("Not yet implemented") => make User information empty
        }

    }

}