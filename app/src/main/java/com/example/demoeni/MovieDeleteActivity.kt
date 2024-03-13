package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.Helpers
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch

class MovieDeleteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        //Envoyer une requete POST à l'API
        lifecycleScope.launch {
            Helpers.showProgressDialog(this@MovieDeleteActivity, "Loading");
            val response = MovieService.MovieApi.retrofitService.delete(User.getInstance()?.getValidToken(), id);
            Helpers.closeProgressDialog()
            if (response.code == "200") {
                Helpers.showAlertDialog(this@MovieDeleteActivity, "The movie was deleted", "Success", MoviesListActivity::class)
            }
            else {
                Helpers.showAlertDialog(this@MovieDeleteActivity, "You seem not authorized to do that..", "Error", null)
            }
        }
        }
    }