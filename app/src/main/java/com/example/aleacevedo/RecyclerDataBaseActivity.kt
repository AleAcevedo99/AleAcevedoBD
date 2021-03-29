package com.example.aleacevedo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aleacevedo.Adapters.DataBaseSurveyAdapter
import com.example.aleacevedo.Adapters.SurveyAdapter
import com.example.aleacevedo.Data.SurveysDB
import com.example.aleacevedo.Tools.Constants
import com.example.aleacevedo.databinding.ActivityMyListBinding
import com.example.aleacevedo.databinding.ActivityRecyclerDataBaseBinding
import com.google.android.material.snackbar.Snackbar

class RecyclerDataBaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerDataBaseBinding
    private var idUser:Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRecyclerDataBaseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.txt_recycler_view_data_base)
        idUser = intent.getLongExtra(Constants.USER, -1)
        if(idUser > -1){
            loadSurveyList()
        }else{
            Snackbar.make(findViewById(R.id.content), "Error al cargar la actividad", Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        loadSurveyList()
    }

    fun loadSurveyList(){
        val db = SurveysDB(this@RecyclerDataBaseActivity)
        val list = db.getAllbyIdUser(idUser)
        //val list = listSurveys.getListArraySurveys(userPosition)

        val adapter = DataBaseSurveyAdapter(list, this@RecyclerDataBaseActivity)

        val linearLayout = LinearLayoutManager(this@RecyclerDataBaseActivity, LinearLayoutManager.VERTICAL,
                false)
        binding.rwsSurveys.layoutManager = linearLayout
        binding.rwsSurveys.adapter = adapter
    }
}
