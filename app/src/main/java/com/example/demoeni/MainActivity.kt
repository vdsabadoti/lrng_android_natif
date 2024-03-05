package com.example.demoeni

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //liaison avec le xml (pour la vue)
        setContentView(R.layout.activiy_registration)
    }
}

