package com.example.aleacevedo.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.util.Log
import com.example.aleacevedo.Contracts.UserContract
import com.example.aleacevedo.Entity.EntityUser

class UsersDB(val context: Context) {
    val connectionDb = ConnectionDB(context)
    private lateinit var db: SQLiteDatabase

    fun add(user:EntityUser): Long{
        db = connectionDb.openConnection(ConnectionDB.MODE_WRITE)

        val values = ContentValues().apply {
            put(UserContract.Entry.COLUMN_EMAIL, user.email)
            put(UserContract.Entry.COLUMN_PASSWORD, user.password)
            put(UserContract.Entry.COLUMN_GENDER, user.gender)
            put(UserContract.Entry.COLUMN_PHONE_NUMBER, user.phoneNumber)
        }
        return db.insert(UserContract.Entry.TABLE_NAME, null, values)
    }

    fun getOnebyEmail(user: EntityUser):Boolean{
        val answer:Boolean
        db = connectionDb.openConnection(ConnectionDB.MODE_READ)

        val projection = arrayOf(BaseColumns._ID) //0

        //val where = "${BaseColumns._ID} = ? AND {StudenContract.Entry.COLUMN_NAME} = ?"
        val where = "${UserContract.Entry.COLUMN_EMAIL} = ?"
        val args = arrayOf(user.email)

        //selection: where, argWhere, sentenciagrpupby, having
        val cursor = db.query(UserContract.Entry.TABLE_NAME, projection, where, args,
                null, null, null)

        answer = cursor.moveToFirst()

        return  answer
    }

    fun getOnebyEmailPassword(user: EntityUser):Long{
        val answer:Long
        db = connectionDb.openConnection(ConnectionDB.MODE_READ)

        val projection = arrayOf(BaseColumns._ID) //0

        //val where = "${BaseColumns._ID} = ? AND {StudenContract.Entry.COLUMN_NAME} = ?"
        val where = "${UserContract.Entry.COLUMN_EMAIL} = ? AND ${UserContract.Entry.COLUMN_PASSWORD} = ?"
        val args = arrayOf(user.email, user.password)

        //selection: where, argWhere, sentenciagrpupby, having
        val cursor = db.query(UserContract.Entry.TABLE_NAME, projection, where, args,
                null, null, null)

        if(cursor.moveToFirst()){
            answer = cursor.getLong(0)
        }else{
            answer = -1
        }

        return  answer
    }

}