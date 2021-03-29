package com.example.aleacevedo.Contracts

import android.provider.BaseColumns

object UserContract {
    object Entry: BaseColumns {
        const val TABLE_NAME = "CTL_USERS"
        const val COLUMN_EMAIL ="Email"
        const val COLUMN_PASSWORD ="Password"
        const val COLUMN_GENDER ="Gender"
        const val COLUMN_PHONE_NUMBER ="PhoneNumber"
    }
}