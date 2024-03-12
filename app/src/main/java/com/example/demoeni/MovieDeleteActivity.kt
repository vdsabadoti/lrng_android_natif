package com.example.demoeni

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.services.MovieService
import com.example.demoeni.utils.User
import kotlinx.coroutines.launch

class MovieDeleteActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val b = intent.extras
        val id = b!!.getInt("id");

        //Envoyer une requete POST à l'API
        lifecycleScope.launch {
            val response = MovieService.MovieApi.retrofitService.delete(User.getInstance()?.getValidToken(), id);
            if (response.code == "200") {
                val builder = AlertDialog.Builder(this@MovieDeleteActivity);
                builder.setTitle("Delete");
                builder.setMessage("Movie deleted");
                builder.setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss();
                    val intent = Intent(applicationContext, MoviesListActivity::class.java);
                    startActivity(intent);
                };
                //afficher le modal
                builder.show();
            }
            else {
                val builder = AlertDialog.Builder(this@MovieDeleteActivity);
                builder.setTitle("Not authorized");
                builder.setMessage(response.message);
                builder.setPositiveButton("KO") { dialog, which ->
                    dialog.dismiss();
                };
                builder.show();
            }

        }



        }
    }