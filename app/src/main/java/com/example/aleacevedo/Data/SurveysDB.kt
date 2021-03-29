package com.example.aleacevedo.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.aleacevedo.Contracts.SurveyContract
import com.example.aleacevedo.Contracts.UserContract
import com.example.aleacevedo.Entity.EntitySurvey
import com.example.aleacevedo.Entity.EntityUser
import com.example.aleacevedo.Tools.Constants
import java.io.Console
import java.text.SimpleDateFormat
import java.util.*

class SurveysDB(val context: Context) {
    val connectionDb = ConnectionDB(context)
    private lateinit var db: SQLiteDatabase

    fun add(survey:EntitySurvey): Long{
        db = connectionDb.openConnection(ConnectionDB.MODE_WRITE)
        val stf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        val values = ContentValues().apply {
            put(SurveyContract.Entry.COLUMN_NAME, survey.name)
            put(SurveyContract.Entry.COLUMN_ID_USER, survey.user)
            put(SurveyContract.Entry.COLUMN_GENDER, survey.gender)
            put(SurveyContract.Entry.COLUMN_AGE_CATEGORY, survey.ageCategory)
            put(SurveyContract.Entry.COLUMN_READ_FRECUENCY, survey.readFrecuency)
            put(SurveyContract.Entry.COLUMN_READ_APP, survey.readApp)
            put(SurveyContract.Entry.COLUMN_RECOMENDATION_PER_AUTHOR, survey.recomendationPerAuthor)
            put(SurveyContract.Entry.COLUMN_RECOMENDATION_PER_GENDER, survey.recomendationsPerGender)
            put(SurveyContract.Entry.COLUMN_SHELF_ORGANIZATION, survey.shelfOrganization)
            put(SurveyContract.Entry.COLUMN_RECOMENDATION_PER_TOPIC, survey.recomendationPerTopic)
            put(SurveyContract.Entry.COLUMN_INTERESTED, survey.interested)
            put(SurveyContract.Entry.COLUMN_SELECT_PER_AUTHOR, survey.selectPerAuthor)
            put(SurveyContract.Entry.COLUMN_SELECT_PER_REVIEW, survey.selectPerReview)
            put(SurveyContract.Entry.COLUMN_SELECT_PER_GENDER, survey.selectPerGender)
            put(SurveyContract.Entry.COLUMN_DISAVANTAGE, survey.disavantage)
            put(SurveyContract.Entry.COLUMN_WRITE_REVIEWS, survey.writeReviews)
            put(SurveyContract.Entry.COLUMN_DATE, sdf.format(survey.date))
            put(SurveyContract.Entry.COLUMN_DATE_SELECTED,  sdf.format(survey.dateSelected))
            put(SurveyContract.Entry.COLUMN_TIME_SELECTED, stf.format(survey.timeSelected))
        }
        return db.insert(SurveyContract.Entry.TABLE_NAME, null, values)
    }

    fun update(survey: EntitySurvey):Int{
        db = connectionDb.openConnection(ConnectionDB.MODE_WRITE)
        val stf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        val values = ContentValues().apply {
            put(SurveyContract.Entry.COLUMN_NAME, survey.name)
            put(SurveyContract.Entry.COLUMN_ID_USER, survey.user)
            put(SurveyContract.Entry.COLUMN_GENDER, survey.gender)
            put(SurveyContract.Entry.COLUMN_AGE_CATEGORY, survey.ageCategory)
            put(SurveyContract.Entry.COLUMN_READ_FRECUENCY, survey.readFrecuency)
            put(SurveyContract.Entry.COLUMN_READ_APP, survey.readApp)
            put(SurveyContract.Entry.COLUMN_RECOMENDATION_PER_AUTHOR, survey.recomendationPerAuthor)
            put(SurveyContract.Entry.COLUMN_RECOMENDATION_PER_GENDER, survey.recomendationsPerGender)
            put(SurveyContract.Entry.COLUMN_SHELF_ORGANIZATION, survey.shelfOrganization)
            put(SurveyContract.Entry.COLUMN_RECOMENDATION_PER_TOPIC, survey.recomendationPerTopic)
            put(SurveyContract.Entry.COLUMN_INTERESTED, survey.interested)
            put(SurveyContract.Entry.COLUMN_SELECT_PER_AUTHOR, survey.selectPerAuthor)
            put(SurveyContract.Entry.COLUMN_SELECT_PER_REVIEW, survey.selectPerReview)
            put(SurveyContract.Entry.COLUMN_SELECT_PER_GENDER, survey.selectPerGender)
            put(SurveyContract.Entry.COLUMN_DISAVANTAGE, survey.disavantage)
            put(SurveyContract.Entry.COLUMN_WRITE_REVIEWS, survey.writeReviews)
            put(SurveyContract.Entry.COLUMN_DATE, sdf.format(survey.date))
            put(SurveyContract.Entry.COLUMN_DATE_SELECTED,  sdf.format(survey.dateSelected))
            put(SurveyContract.Entry.COLUMN_TIME_SELECTED, stf.format(survey.timeSelected))
        }

        val where = "${BaseColumns._ID} = ?"
        val args = arrayOf(survey.id.toString())
        return db.update(SurveyContract.Entry.TABLE_NAME, values, where, args)
    }

    fun getOne(id: Long):EntitySurvey{
        val survey = EntitySurvey()
        db = connectionDb.openConnection(ConnectionDB.MODE_READ)
        //Atributos que quiere que se vena
        val projection = arrayOf(BaseColumns._ID, //0
            SurveyContract.Entry.COLUMN_ID_USER, //1
            SurveyContract.Entry.COLUMN_NAME, //2
            SurveyContract.Entry.COLUMN_GENDER, //3
            SurveyContract.Entry.COLUMN_AGE_CATEGORY, //4
            SurveyContract.Entry.COLUMN_READ_FRECUENCY, //5
            SurveyContract.Entry.COLUMN_READ_APP, //6
            SurveyContract.Entry.COLUMN_RECOMENDATION_PER_AUTHOR, //7
            SurveyContract.Entry.COLUMN_RECOMENDATION_PER_GENDER, //8
            SurveyContract.Entry.COLUMN_SHELF_ORGANIZATION, //9
            SurveyContract.Entry.COLUMN_RECOMENDATION_PER_TOPIC, //10
            SurveyContract.Entry.COLUMN_INTERESTED, //11
            SurveyContract.Entry.COLUMN_SELECT_PER_AUTHOR, //12
            SurveyContract.Entry.COLUMN_SELECT_PER_REVIEW, //13
            SurveyContract.Entry.COLUMN_SELECT_PER_GENDER, //14
            SurveyContract.Entry.COLUMN_DISAVANTAGE, //15
            SurveyContract.Entry.COLUMN_WRITE_REVIEWS, //16
            SurveyContract.Entry.COLUMN_DATE, //17
            SurveyContract.Entry.COLUMN_DATE_SELECTED, //18
            SurveyContract.Entry.COLUMN_TIME_SELECTED) //19

        val where = "${BaseColumns._ID} = ?"
        val args = arrayOf(id.toString())

        //selection: where, argWhere, sentenciagrpupby, having
        val cursor = db.query(SurveyContract.Entry.TABLE_NAME, projection, where, args,
                null, null, null)
        val stf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        if(cursor.moveToFirst()){
            survey.id = cursor.getLong(0)
            survey.user = cursor.getLong(1)
            survey.name = cursor.getString(2)
            survey.gender = cursor.getInt(3)
            survey.ageCategory = cursor.getInt(4)
            survey.readFrecuency = cursor.getInt(5)
            survey.readApp = cursor.getInt(6) == 1
            survey.recomendationPerAuthor = cursor.getInt(7) == 1
            survey.recomendationsPerGender = cursor.getInt(8) == 1
            survey.shelfOrganization = cursor.getInt(9) == 1
            survey.recomendationPerTopic = cursor.getInt(10) == 1
            survey.interested = cursor.getInt(11) == 1
            survey.selectPerAuthor = cursor.getInt(12) == 1
            survey.selectPerReview = cursor.getInt(13) == 1
            survey.selectPerGender = cursor.getInt(14) == 1
            survey.disavantage = cursor.getString(15)
            survey.writeReviews = cursor.getInt(16) == 1
            survey.date = sdf.parse(cursor.getString(17))
            survey.dateSelected =  sdf.parse(cursor.getString(18))
            survey.timeSelected =  stf.parse(cursor.getString(19))
        }

        return survey
    }

    fun getAllbyIdUser(idUser: Long): ArrayList<EntitySurvey>{
        var answerList = arrayListOf<EntitySurvey>()
        db = connectionDb.openConnection(ConnectionDB.MODE_READ)
        //Atributos que quiere que se vena
        val projection = arrayOf(BaseColumns._ID, //0
                SurveyContract.Entry.COLUMN_ID_USER, //1
                SurveyContract.Entry.COLUMN_NAME, //2
                SurveyContract.Entry.COLUMN_GENDER, //3
                SurveyContract.Entry.COLUMN_AGE_CATEGORY, //4
                SurveyContract.Entry.COLUMN_READ_FRECUENCY, //5
                SurveyContract.Entry.COLUMN_READ_APP, //6
                SurveyContract.Entry.COLUMN_RECOMENDATION_PER_AUTHOR, //7
                SurveyContract.Entry.COLUMN_RECOMENDATION_PER_GENDER, //8
                SurveyContract.Entry.COLUMN_SHELF_ORGANIZATION, //9
                SurveyContract.Entry.COLUMN_RECOMENDATION_PER_TOPIC, //10
                SurveyContract.Entry.COLUMN_INTERESTED, //11
                SurveyContract.Entry.COLUMN_SELECT_PER_AUTHOR, //12
                SurveyContract.Entry.COLUMN_SELECT_PER_REVIEW, //13
                SurveyContract.Entry.COLUMN_SELECT_PER_GENDER, //14
                SurveyContract.Entry.COLUMN_DISAVANTAGE, //15
                SurveyContract.Entry.COLUMN_WRITE_REVIEWS, //16
                SurveyContract.Entry.COLUMN_DATE, //17
                SurveyContract.Entry.COLUMN_DATE_SELECTED, //18
                SurveyContract.Entry.COLUMN_TIME_SELECTED) //19

        val where = "${SurveyContract.Entry.COLUMN_ID_USER} = ?"
        val args = arrayOf(idUser.toString())

        val cursor = db.query(SurveyContract.Entry.TABLE_NAME, projection, where, args,
                null, null, null)

        val stf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

        if(cursor.moveToFirst()){
            do{
                val survey = EntitySurvey()
                survey.id = cursor.getLong(0)
                survey.user = cursor.getLong(1)
                survey.name = cursor.getString(2)
                survey.gender = cursor.getInt(3)
                survey.ageCategory = cursor.getInt(4)
                survey.readFrecuency = cursor.getInt(5)
                survey.readApp = cursor.getInt(6) == 1
                survey.recomendationPerAuthor = cursor.getInt(7) == 1
                survey.recomendationsPerGender = cursor.getInt(8) == 1
                survey.shelfOrganization = cursor.getInt(9) == 1
                survey.recomendationPerTopic = cursor.getInt(10) == 1
                survey.interested = cursor.getInt(11) == 1
                survey.selectPerAuthor = cursor.getInt(12) == 1
                survey.selectPerReview = cursor.getInt(13) == 1
                survey.selectPerGender = cursor.getInt(14) == 1
                survey.disavantage = cursor.getString(15)
                survey.writeReviews = cursor.getInt(16) == 1
                survey.date = sdf.parse(cursor.getString(17))
                survey.dateSelected =  sdf.parse(cursor.getString(18))
                survey.timeSelected =  stf.parse(cursor.getString(19))
                answerList.add(survey)

            }while (cursor.moveToNext())
        }

        return  answerList
    }

    fun getOnebyNameUserForUpdate(survey: EntitySurvey):Boolean{
        db = connectionDb.openConnection(ConnectionDB.MODE_READ)

        //Atributos que quiere que se vena
        val projection = arrayOf(BaseColumns._ID) //0

        val where = "${SurveyContract.Entry.COLUMN_ID_USER} = ? AND ${SurveyContract.Entry.COLUMN_NAME} = ? AND ${BaseColumns._ID} != ?"
        val args = arrayOf(survey.user.toString(), survey.name, survey.id.toString())

        //selection: where, argWhere, sentenciagrpupby, having
        val cursor = db.query(SurveyContract.Entry.TABLE_NAME, projection, where, args,
                null, null, null)

        return cursor.moveToFirst()
    }

    fun getOnebyNameUserForAdd(survey: EntitySurvey):Boolean{
        db = connectionDb.openConnection(ConnectionDB.MODE_READ)

        //Atributos que quiere que se vena
        val projection = arrayOf(BaseColumns._ID) //0

        val where = "${SurveyContract.Entry.COLUMN_ID_USER} = ? AND ${SurveyContract.Entry.COLUMN_NAME} = ?"
        val args = arrayOf(survey.user.toString(), survey.name)

        //selection: where, argWhere, sentenciagrpupby, having
        val cursor = db.query(SurveyContract.Entry.TABLE_NAME, projection, where, args,
            null, null, null)

        return cursor.moveToFirst()
    }

    fun delete(id: Long):Int{
        db = connectionDb.openConnection(ConnectionDB.MODE_WRITE)

        //Los signos de interrogacion se reemplazan por los argumentos que se pasen
        val where = "${BaseColumns._ID} = ?"
        val args = arrayOf(id.toString())
        return db.delete(SurveyContract.Entry.TABLE_NAME, where, args)
    }





}