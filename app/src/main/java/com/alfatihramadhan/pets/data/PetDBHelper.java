package com.alfatihramadhan.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alfatihramadhan.pets.data.PetContract.PetEntry;

/**
 * Created by alfatih on 5/24/2017.
 * Class DB Helper to create, open and manage database connection
 */

public class PetDBHelper extends SQLiteOpenHelper{

    // create constant for database name and database version
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    // use deffault cursor factory
    // intstead of using public PetDBHelper(Context context, SQLiteDatabase.CursorFactory factory)
    public PetDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    * Getting a Database Connection
    * Activity request a readable database -> SQLiteOpenHelper subclass check is there a database ->
    * 1. if database doesn't exist make a database using onCreate() method
    * -> make an instance of SQLiteDatabase object and return this object to the activity that ask for the database
    * 2. if database already exist SQLiteOpenHelper subclass instance won't call onCreate() instead,
     * it will make an instance of SQLiteDatabase object couple with the existing database and return that object to the activity that requested it
    * Remember that onCreate() method only called if there is no existing database (when the database is first created)
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String SQL_CREATE_TABLE = "CREATE TABLE "
                 + PetEntry.TABLE_NAME + " ("
                 + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL,"
                 + PetEntry.COLUMN_PET_BREED + " TEXT,"
                 + PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL,"
                 + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";
        // execSQL method is a method from SQLiteDatabase class to modified database configuration and structure
        Log.e("SQL QUERY",SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    /*
    * onUpgrade() method is for when the database schema of the database change (ex: adding a different column)
    * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
