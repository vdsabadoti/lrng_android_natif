package com.example.demoeni

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.utils.Helpers
import kotlinx.coroutines.launch

class MovieDeleteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        delete(id, this@MovieDeleteActivity);

        }

    private fun delete(id : Int, context : Context) {
        lifecycleScope.launch {
            Helpers.showProgressDialog(context, "Loading");
            val response = com.example.demoeni.services.MovieService.MovieApi.retrofitService.delete(com.example.demoeni.utils.User.getInstance()?.getValidToken(), id);
            Helpers.closeProgressDialog()
            if (response.code == "200") {
                Helpers.showAlertDialog(context, "The movie was deleted", "Success", com.example.demoeni.MoviesListActivity::class)
            }
            else {
                Helpers.showAlertDialog(context, "You seem not authorized to do that..", "Error", null)
            }
        }
    }

    }