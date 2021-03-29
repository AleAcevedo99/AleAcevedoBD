package com.example.aleacevedo.Data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.aleacevedo.Contracts.SurveyContract
import com.example.aleacevedo.Contracts.UserContract

class ConnectionDB(val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USERS)
        db?.execSQL(CREATE_TABLE_SURVEYS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_SURVEYS)
        db?.execSQL(DROP_TABLE_USERS)
        onCreate(db)
    }

    //Siempre que se debe acceder a la BD se debe especificar el modo
    fun openConnection(typeConnection: Int): SQLiteDatabase {
        return when(typeConnection){
            MODE_WRITE->{
                writableDatabase
            }
            MODE_READ->{
                readableDatabase
            }
            else->{
                readableDatabase
            }
        }
    }

    companion object{
        //Si se cambia algo del modelo de la BD hay que cambiar la version o hay problemas al exe
        const val DATABASE_NAME = "SURVEYS_DB"
        const val DATABASE_VERSION = 2

        const val TABLE_USERS = "CTL_USERS"
        const val CREATE_TABLE_USERS = "CREATE TABLE ${UserContract.Entry.TABLE_NAME} " +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${UserContract.Entry.COLUMN_EMAIL} VARCHAR(20), " +
                " ${UserContract.Entry.COLUMN_PASSWORD} VARCHAR(20), " +
                " ${UserContract.Entry.COLUMN_GENDER} INTEGER, " +
                " ${UserContract.Entry.COLUMN_PHONE_NUMBER} VARCHAR(20))"

        const val DROP_TABLE_USERS = "DROP TABLE IF EXISTS $TABLE_USERS"

        const val TABLE_SURVEYS = "CTL_SURVEYS"
        const val CREATE_TABLE_SURVEYS = "CREATE TABLE ${SurveyContract.Entry.TABLE_NAME} " +
                "( ${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                " ${SurveyContract.Entry.COLUMN_NAME} VARCHAR(50), " +
                " ${SurveyContract.Entry.COLUMN_ID_USER} INTEGER, " +
                " ${SurveyContract.Entry.COLUMN_GENDER} INTEGER, " +
                " ${SurveyContract.Entry.COLUMN_AGE_CATEGORY} INTEGER, " +
                " ${SurveyContract.Entry.COLUMN_READ_FRECUENCY} INTEGER, " +
                " ${SurveyContract.Entry.COLUMN_READ_APP} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_RECOMENDATION_PER_AUTHOR} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_RECOMENDATION_PER_GENDER} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_RECOMENDATION_PER_TOPIC} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_SHELF_ORGANIZATION} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_INTERESTED} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_SELECT_PER_AUTHOR} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_SELECT_PER_GENDER} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_SELECT_PER_REVIEW} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_DISAVANTAGE} VARCHAR(100), " +
                " ${SurveyContract.Entry.COLUMN_WRITE_REVIEWS} BOOLEAN, " +
                " ${SurveyContract.Entry.COLUMN_DATE} DATE, " +
                " ${SurveyContract.Entry.COLUMN_DATE_SELECTED} DATE, " +
                " ${SurveyContract.Entry.COLUMN_TIME_SELECTED} DATE, " +
                " FOREIGN KEY(${SurveyContract.Entry.COLUMN_ID_USER}) REFERENCES $TABLE_USERS(${BaseColumns._ID}))"

        const val DROP_TABLE_SURVEYS = "DROP TABLE IF EXISTS $TABLE_SURVEYS"

        const val MODE_READ = 1
        const val MODE_WRITE = 2
    }
}