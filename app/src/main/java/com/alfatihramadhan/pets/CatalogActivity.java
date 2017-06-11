package com.alfatihramadhan.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.alfatihramadhan.pets.adapter.PetCursorAdapter;
import com.alfatihramadhan.pets.data.PetContract;
import com.alfatihramadhan.pets.data.PetContract.PetEntry;

import java.util.List;

/*
* Class definition/declaration inherit from AppCompatActivity
* */
public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call superclass constructor
        super.onCreate(savedInstanceState);
        // set layout activity
        setContentView(R.layout.activity_main);

        // find view on the view hierarchy
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        // setup floating tab button action event
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create intent
                Intent intent = new Intent(CatalogActivity.this,EditorActivity.class);
                // start intent
                startActivity(intent);
            }
        });

        displayDatabaseInfo();

    }

    /*
    * Create option menu
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate layout menu from layout resource file
        getMenuInflater().inflate(R.menu.menu_catalog,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // if selected menu is insert dummy data
        if(item.getItemId()== R.id.action_insert_dummy_data){
            insertDummyPet();
            return true;
        }
        // if selected menu is delete all entries
        else if(item.getItemId()== R.id.action_delete_all_entries){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
   * Insert dummy pet to database
   * */
    private void insertDummyPet() {

        // create content values as input for insert database
        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME,"Toto");
        values.put(PetEntry.COLUMN_PET_BREED,"Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT,7);

        // get insert method content resolver return value
        Uri uri = getContentResolver().insert(PetContract.CONTENT_URI,values);
        Toast.makeText(this, "Pet saved with row id uri : " + uri.toString(), Toast.LENGTH_SHORT).show();


    }

    /*
    * Read database object
    * */
    void displayDatabaseInfo(){

        // perform this raw SQL query "Select * from pets"
        // to get a cursor that contains all rows from the pets table
        // Cursor cursor = db.rawQuery("SELECT * FROM " + PetEntry.TABLE_NAME,null);

        // contain our column name that we want to insert in
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_GENDER,
                PetEntry.COLUMN_PET_WEIGHT};

        // the column for the WHERE clause
        String selection = PetEntry.COLUMN_PET_GENDER + "=?";
        // the values for the WHERE clause
        String[] selectionArgs = new String[] {String.valueOf(PetEntry.GENDER_FEMALE)};

        // create cursor from content resolver
        Cursor cursor = getContentResolver().query(
                PetContract.CONTENT_URI
                ,projection
                ,null
                ,null
                ,null);

        // find view
        ListView mListViewPet = (ListView)findViewById(R.id.pet_list_view);
        // create petCursorAdapterInstance object
        PetCursorAdapter mPetCursorAdapter = new PetCursorAdapter(this,cursor);
        // setup listview adapter
        mListViewPet.setAdapter(mPetCursorAdapter);

        // find view
        LinearLayout mEmptyPet = (LinearLayout) findViewById(R.id.empty_pet);
        // setup view if list empty
        mListViewPet.setEmptyView(mEmptyPet);
    }
}