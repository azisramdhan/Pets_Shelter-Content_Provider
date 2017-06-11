package com.alfatihramadhan.pets;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alfatihramadhan.pets.data.PetContract;
import com.alfatihramadhan.pets.data.PetContract.PetEntry;
import com.alfatihramadhan.pets.data.PetDBHelper;

import java.util.ArrayList;
import java.util.List;

/*
* Class definition/declaration inherit from AppCompatActivity
* */
public class EditorActivity extends AppCompatActivity {

    // define petDBHelper object
    PetDBHelper petDBHelper;

    // pet overview
    EditText mNameEditText;
    EditText mBreedEditText;

    // pet measurement
    EditText mWeightEditText;

    // pet gender
    int mGender;

    Spinner mGenderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call superclass constructor
        super.onCreate(savedInstanceState);
        // set layout activity
        setContentView(R.layout.activity_editor);
        // setup spinner
        setupSpinner();

        // find view
        mNameEditText = (EditText)findViewById(R.id.edit_text_name);
        mBreedEditText = (EditText)findViewById(R.id.edit_text_breed);
        mWeightEditText = (EditText)findViewById(R.id.edit_text_weight);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String sGender = (String)adapterView.getItemAtPosition(position);
                if(sGender.equals("Male")){
                    mGender = PetEntry.GENDER_MALE;
                }else if(sGender.equals("Female")){
                    mGender = PetEntry.GENDER_FEMALE;
                }else{
                    mGender = PetEntry.GENDER_UNKNOWN;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mGender = PetEntry.GENDER_UNKNOWN;
            }
        });

        // create petDBHelper object instance
        petDBHelper = new PetDBHelper(this);
    }

    /*
    * Create option menu
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate layout menu from layout resource file
        getMenuInflater().inflate(R.menu.menu_editor,menu);
        return true;
    }

    /*
    * Setup gender spinner
    * */
    void setupSpinner(){
        // find view
        mGenderSpinner = (Spinner)findViewById(R.id.spinner_gender);
        // create a list of string object
        List<String> mGender = new ArrayList<>();
        // add item list
        mGender.add("Unknown");
        mGender.add("Male");
        mGender.add("Female");
        // create adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mGender);
        // setup spinner adapter
        mGenderSpinner.setAdapter(arrayAdapter);
    }

    /*
    * Action event when option menu selected
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // if selected menu is insert dummy data
        if(item.getItemId()== R.id.action_save){
            insertPet();
            return true;
        }
        // if selected menu is delete all entries
        else if(item.getItemId()== R.id.action_delete){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
* Insert pet to database
* */
    private void insertPet() {

        // create content values as input for insert database
        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME,mNameEditText.getText().toString());
        values.put(PetContract.PetEntry.COLUMN_PET_BREED,mBreedEditText.getText().toString());
        values.put(PetContract.PetEntry.COLUMN_PET_GENDER,mGender);
        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT,Integer.parseInt(mWeightEditText.getText().toString()));

        // get insert method content resolver return value
        Uri uri = getContentResolver().insert(PetContract.CONTENT_URI,values);
        Toast.makeText(this, "Pet saved with row id uri : " + uri.toString(), Toast.LENGTH_SHORT).show();

    }
}
