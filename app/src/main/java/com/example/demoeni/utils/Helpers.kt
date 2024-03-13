package com.example.demoeni.utils

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass

class Helpers {

    companion object {

        var progressDialog : ProgressDialog? = null;

        fun showProgressDialog(context : Context, message : String) {
            progressDialog = ProgressDialog(context) ;
            progressDialog?.setTitle("Loading");
            progressDialog?.setMessage(message);
            progressDialog?.show();
        }

        fun closeProgressDialog() {
            progressDialog?.dismiss()
        }

        var builder : AlertDialog.Builder? = null;
        fun showAlertDialog(context: Context, message : String, title : String, classType: KClass<*>?){
            builder = AlertDialog.Builder(context);
            builder!!.setTitle(title);
            builder!!.setMessage(message);
            builder!!.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss();
                if (classType != null){
                    openActivity(context, classType);
                }
            };
            builder!!.show();
        }
        fun openActivity(context : Context, classType: KClass<*>){
            val intent = Intent(context, classType.java);
            context.startActivity(intent);
        }

    }

}