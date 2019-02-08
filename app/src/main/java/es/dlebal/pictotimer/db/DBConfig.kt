package es.dlebal.pictotimer.db

import android.provider.BaseColumns

/**
 * Object object DBConfig
 *
 * This object provides DBConfig objects
 */
object DBConfig {

    // Database
    const val DATABASE_VERSION = 1 // Database version
    const val DATABASE_NAME = "PictoTimer.db" // Database name

    // Inner class that defines the table tasks
    object TasksEntry : BaseColumns {
        const val TABLE_NAME = "tasks"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PICTOGRAM = "pictogram"
        const val COLUMN_TIMER = "timer"
    }

    // SQL sentences
    val SQL_CREATE_ENTRIES = "CREATE TABLE " + TasksEntry.TABLE_NAME + " (" +
            TasksEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
            TasksEntry.COLUMN_NAME + " TEXT, " +
            TasksEntry.COLUMN_PICTOGRAM + " INTEGER, " +
            TasksEntry.COLUMN_TIMER + " INTEGER) " // SQL create entries
    val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TasksEntry.TABLE_NAME // SQL delete entries

}
