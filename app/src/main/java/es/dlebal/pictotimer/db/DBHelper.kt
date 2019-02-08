package es.dlebal.pictotimer.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import es.dlebal.pictotimer.models.Task

/**
 * Class class DBHelper(context: Context) : SQLiteOpenHelper(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION)
 *
 * This class provides DBHelper objects
 *
 * Properties:
 *   - context: Context
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION) {

    /**
     * Constants
     */
    private val className: String = DBHelper::class.java.simpleName // Class name

    /**
     * Method override fun onCreate(db: SQLiteDatabase)
     *
     * Performs the following tasks:
     *   - Initialize the database
     *
     * Parameters:
     *   @param db: The database
     */
    override fun onCreate(db: SQLiteDatabase) {

        // Constants
        val methodName = "onCreate" // Method name

        // Initialize the database
        Log.d(this.className + ": $methodName", "Initialize the database")
        db.execSQL(DBConfig.SQL_CREATE_ENTRIES)

    }

    /**
     * Method override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
     *
     * Performs the following tasks:
     *   - Upgrade the database
     *
     * Parameters:
     *   @param db: The database
     *   @param oldVersion: The old database version
     *   @param newVersion: The new database version
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // Constants
        val methodName = "onUpgrade" // Method name

        // Upgrade the database
        Log.d(this.className + ": $methodName", "Upgrade the database")
        db.execSQL(DBConfig.SQL_DELETE_ENTRIES)
        onCreate(db)

    }

    /**
     * Method override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
     *
     * Performs the following tasks:
     *   - Downgrade the database
     *
     * Parameters:
     *   @param db: The database
     *   @param oldVersion: The old database version
     *   @param newVersion: The new database version
     */
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // Constants
        val methodName = "onDowngrade" // Method name

        // Downgrade the database
        Log.d(this.className + ": $methodName", "Downgrade the database")
        onUpgrade(db, oldVersion, newVersion)

    }

    /**
     * Method fun addTask(task: Task): Boolean
     *
     * Performs the following tasks:
     *   - Add a task
     *   - Return the insert result
     *
     * Parameters:
     *   @param task: Task
     *   @return Boolean: The insert result
     */
    fun addTask(task: Task): Boolean {

        // Constants
        val methodName = "addTask" // Method name

        // Add a task
        Log.d(this.className + ": $methodName", "Add a task")
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBConfig.TasksEntry.COLUMN_ID, task.id)
        values.put(DBConfig.TasksEntry.COLUMN_NAME, task.name)
        values.put(DBConfig.TasksEntry.COLUMN_PICTOGRAM, task.pictogram)
        values.put(DBConfig.TasksEntry.COLUMN_TIMER, task.timer)
        val newRowId = db.insert(DBConfig.TasksEntry.TABLE_NAME, null, values)
        db.close()

        // Return the insert result
        Log.d(this.className + ": $methodName", "Return the insert result")
        return (Integer.parseInt("$newRowId") != -1)

    }

}
