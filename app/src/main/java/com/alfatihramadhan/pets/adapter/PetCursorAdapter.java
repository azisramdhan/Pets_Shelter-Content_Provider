package com.alfatihramadhan.pets.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.alfatihramadhan.pets.R;
import com.alfatihramadhan.pets.data.PetContract;
import com.alfatihramadhan.pets.data.PetContract.PetEntry;
import com.alfatihramadhan.pets.entity.Pet;

import java.util.List;

/**
 * Created by Alkha on 6/11/2017.
 * Pet custom adapter
 */

public class PetCursorAdapter extends CursorAdapter{

    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mNamePet = (TextView)view.findViewById(R.id.pet_name);
        TextView mBreedPet = (TextView)view.findViewById(R.id.pet_breed);

        String columnName = cursor.getString(cursor.getColumnIndexOrThrow(PetEntry.COLUMN_PET_NAME));
        String columnBreed =  cursor.getString(cursor.getColumnIndexOrThrow(PetEntry.COLUMN_PET_BREED));

        mNamePet.setText(columnName);
        mBreedPet.setText(columnBreed);

    }
}
