package com.example.demoeni.utils

import android.app.ProgressDialog
import android.content.Context

class Helpers {

    companion object {

        var progressDialog : ProgressDialog? = null;

        fun showProgressDialog(context : Context, message : String) {
            progressDialog = ProgressDialog(context) ;
            progressDialog?.setTitle("Loading");
            progressDialog?.setMessage(message);
            progressDialog?.show();
        }

        fun close(_context : Context, _message : String) {
            progressDialog?.dismiss()
        }

    }

}