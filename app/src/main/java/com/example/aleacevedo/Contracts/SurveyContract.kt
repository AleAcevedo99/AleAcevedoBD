package com.example.aleacevedo.Contracts

import android.provider.BaseColumns
import java.util.*

object SurveyContract {
    object Entry: BaseColumns {
        const val TABLE_NAME = "CTL_SURVEYS"
        const val COLUMN_NAME ="Name"
        const val COLUMN_ID_USER ="User"
        const val COLUMN_GENDER ="Gender"
        const val COLUMN_AGE_CATEGORY ="AgeCategory"
        const val COLUMN_READ_FRECUENCY ="ReadFrecuency"
        const val COLUMN_READ_APP ="ReadApp"
        const val COLUMN_RECOMENDATION_PER_AUTHOR ="RecomendationPerAuthor"
        const val COLUMN_RECOMENDATION_PER_GENDER ="RecomendationPerGender"
        const val COLUMN_SHELF_ORGANIZATION ="ShelfOrganization"
        const val COLUMN_RECOMENDATION_PER_TOPIC ="RecomendationPerTopic"
        const val COLUMN_INTERESTED ="Interested"
        const val COLUMN_SELECT_PER_AUTHOR ="SelectPerAuthor"
        const val COLUMN_SELECT_PER_REVIEW ="SelectPerReview"
        const val COLUMN_SELECT_PER_GENDER ="SelectPerGender"
        const val COLUMN_DISAVANTAGE="Disavantage"
        const val COLUMN_WRITE_REVIEWS ="WirteReviews"
        const val COLUMN_DATE ="Date"
        const val COLUMN_DATE_SELECTED ="DateSelected"
        const val COLUMN_TIME_SELECTED="TimeSelected"
    }
}
