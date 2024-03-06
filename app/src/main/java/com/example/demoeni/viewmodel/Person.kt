package com.example.demoeni.viewmodel

class Person(
    var mail: String? = "", var nickname: String? = "", var password: String? = "", var city: String? = "", var postalCode : String? = null,
    var phoneNumber : String? = null
) {

    override fun equals(other: Any?): Boolean {
        if (other is Person) {
            mail == other.mail;
        }
        return super.equals(other);
    }

}
