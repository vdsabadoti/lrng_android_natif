package com.example.demoeni

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.demoeni.databinding.ActivityListPersonsBinding
import com.example.demoeni.services.PersonService
import com.example.demoeni.viewmodel.Person
import kotlinx.coroutines.launch

class ListPersonsActivity : ComponentActivity() {

   lateinit var vm : ActivityListPersonsBinding;
    lateinit var dataList : MutableLiveData<MutableList<Person>>;
    lateinit var adapter : PersonAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Chargement de la page
        vm = DataBindingUtil.setContentView(this, R.layout.activity_list_persons);

        //Connecter adapter custom (cellule person) au RecyclerView || pointage au même adresse mémoire
        val adapter = PersonAdapter();
        vm.rvPersons.adapter = adapter;

        //Alimenter les données dans un ArrayList en patter Observable (mise à jour de données en temps réel)
        // (populate)
        //dataList = MutableLiveData<MutableList<Person>>(mutableListOf(Person("vds@gmail.com"), Person("abc@gmail.com")))
        //adapter.submitList(dataList.value);

        //Recuperer les données d'un API
        lifecycleScope.launch {
            val persons = PersonService.PersonApi.retrofitService.getPersons()
            adapter.submitList(persons);
        }


        }

        fun refresh(view: View){
            lifecycleScope.launch {
                val persons = PersonService.PersonApi.retrofitService.getPersons()
                adapter.submitList(persons);
            }

        }
    }
