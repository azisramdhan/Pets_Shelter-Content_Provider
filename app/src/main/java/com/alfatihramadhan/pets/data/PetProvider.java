package com.alfatihramadhan.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alfatihramadhan.pets.data.PetContract.PetEntry;

/**
 * Created by Alkha on 6/11/2017.
 * Content Provider sub class
 */

public class PetProvider extends ContentProvider {

    PetDBHelper petDBHelper;

    static final int PETS = 100;
    static final int PET_ID = 101;

    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS,PETS);
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS + "/#",PET_ID);
    }

    @Override
    public boolean onCreate() {
        petDBHelper = new PetDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        // create database connection to acces a database
        SQLiteDatabase db = petDBHelper.getReadableDatabase();

        // define cursor object
        Cursor cursor;

        // number URI id
        int match = uriMatcher.match(uri);
        switch (match){
            case PETS:
                // select all data
                cursor = db.query(PetEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case PET_ID:
                // column name
                selection = PetEntry._ID + "=?";
                // column value
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                // select data by id
                cursor = db.query(PetEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                // unknown URI
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        // return location of record that ask from activity
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase db = petDBHelper.getWritableDatabase();

        long id;

        // number URI id
        int match = uriMatcher.match(uri);
        switch (match){
            case PETS:
                // insert data
                id = db.insert(PetEntry.TABLE_NAME,null,contentValues);
                break;
            default:
                // unknown URI
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return ContentUris.withAppendedId(PetContract.CONTENT_URI,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
