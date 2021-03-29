package com.example.aleacevedo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aleacevedo.Data.SurveysDB
import com.example.aleacevedo.Entity.ListSurveys
import com.example.aleacevedo.Tools.Constants
import com.example.aleacevedo.databinding.ActivityDetailBinding
import com.example.aleacevedo.databinding.ActivityHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var listSurveys = ListSurveys()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val idSurvey = intent.getLongExtra(Constants.ID_SURVEY, -1)


        supportActionBar?.setTitle(R.string.txt_detail)

        if(idSurvey > -1){
            //var survey = listSurveys.getSurvey(name, userPosition)
            val db = SurveysDB(this@DetailActivity)
            val survey = db.getOne(idSurvey)
            if(survey.id > 0){
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val sdt = SimpleDateFormat("HH:mm")

                binding.txvName.setText(survey.name)

                binding.txvGender.text = if (survey.gender == 1) "Masculino" else "Femenino"

                binding.txvReadFrecuency.text = "Lee: ${if (survey.readFrecuency == 1) "Muy frecuentemente"
                else if(survey.readFrecuency == 2) "De vez en cuando"
                else if(survey.readFrecuency == 3) "Poco" 
                else "No especificado"}"

                binding.txvReadApp.text = if (survey.readApp) "Usa una app de libros" else "No usa una app de libros"

                binding.txvFeautres.text = "Funcionalidades que le interesan: ${if (survey.recomendationPerAuthor) "Recomendaciones por autor" else ""}" +
                        " ${if (survey.recomendationsPerGender) "Recomendaciones por género" else ""}" +
                        " ${if (survey.shelfOrganization) "Organización de libreros" else ""} " +
                        "${if (survey.recomendationPerTopic) "Recomendaciones por trama" else ""} " +
                        "${if (!survey.recomendationPerAuthor && !survey.recomendationsPerGender
                                && !survey.shelfOrganization && !survey.recomendationPerTopic) "Sin registrar" else ""}"

                binding.txvAgeCategory.text = "Literatura ${if (survey.ageCategory == 1) "Infantil"
                else if(survey.ageCategory == 2) "Juvenil"
                else if(survey.ageCategory == 3) "New Adult"
                else "Adulta"}"

                binding.txvSelectBooks.text = "Para seleccionar un libro se basa en: ${if (survey.selectPerAuthor) "Autor" else ""}" +
                        " ${if (survey.selectPerGender) "Género" else ""}" +
                        " ${if (survey.selectPerReview) "Reseñas" else ""}" +
                        "${if (!survey.selectPerGender && !survey.selectPerReview
                                && !survey.selectPerAuthor) "Sin registrar" else ""}"

                binding.txvDisavantage.setText("Desventaja que encuentra en las app actuales: ${survey.disavantage}")

                binding.txvWriteReviews.text = if (survey.writeReviews) "Le gusta escribir reseñas de libros"
                else "No le gusta escribir reseñas de libros"

                binding.txvInterested.text = if(survey.interested) "Interesado en la app" else "No interesado en la app"

                binding.txvDate.text = "Fecha: ${simpleDateFormat.format(survey.date)}"
                binding.txvDateSelected.text = "Fecha ingresada: ${sdf.format(survey.dateSelected)}"
                binding.txvTimeSelected.text = "Tiempo ingresado: ${sdt.format(survey.timeSelected)}"

            }else{
                Toast.makeText(this@DetailActivity, "Error al cargar la actividad", Toast.LENGTH_SHORT).show()
                finish()
            }
        }else{
            Toast.makeText(this@DetailActivity, "Error al cargar la actividad", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}